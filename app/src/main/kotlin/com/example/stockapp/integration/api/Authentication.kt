package com.example.stockapp.integration.api

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.provider.Settings.Global.getString
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.runtime.collectAsState
import androidx.core.app.ActivityCompat.startIntentSenderForResult
import androidx.lifecycle.ViewModel
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.tasks.Tasks
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.appcheck.appCheck
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.initialize
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Authentication @Inject constructor(@ApplicationContext private val context: Context) {

    private val auth: FirebaseAuth
    private val _state = MutableStateFlow(AuthenticationState())
    val state: StateFlow<AuthenticationState> = _state.asStateFlow()

    private var oneTapClient: SignInClient
    private var signInRequest: BeginSignInRequest
    var onSignInRequired: ((IntentSender) -> Unit)? = null
    val database = FirebaseDatabase.getInstance()
    init {
        Firebase.initialize(context = context)
        Firebase.appCheck.installAppCheckProviderFactory(
            PlayIntegrityAppCheckProviderFactory.getInstance()
        )
        auth = Firebase.auth
        oneTapClient = Identity.getSignInClient(context)
        signInRequest = BeginSignInRequest.builder()
            .setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions.builder()
                .setSupported(true)
                .build())
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    // Your server's client ID, not your Android client ID.
                    .setServerClientId("628293109266-5u6pd3t88uie9eg0bjjrr1pqe5trt8s7.apps.googleusercontent.com")
                    // Only show accounts previously used to sign in.
                    .setFilterByAuthorizedAccounts(false)
                    .build())
            // Automatically sign in when exactly one credential is retrieved.
            .setAutoSelectEnabled(true)
            .build()

        auth.addAuthStateListener { firebaseAuth ->
            val firebaseUser = firebaseAuth.currentUser
            if (firebaseUser != null) {
                _state.update { currentState ->
                    currentState.copy(
                        currentUser = firebaseUser,
                        loggedIn = true,
                        userId = firebaseUser.uid)
                }
                checkUserCreated() { userCreated ->
                    if (userCreated) {
                        _state.update { currentState ->
                            currentState.copy(
                                userCreated = true,
                                ready =  true,
                            )
                        }
                    }
                    else {
                        _state.update { currentState ->
                            currentState.copy(
                                userCreated = false,
                                ready =  true,
                            )
                        }
                    }
                }
            } else {
                _state.update { currentState ->
                    currentState.copy(
                        currentUser = null,
                        loggedIn = false,
                        userId = "",
                        userCreated = false,
                        ready =  true,

                    )
                }
            }
        }
    }

    fun displayGoogleUISignIn() {
        oneTapClient.beginSignIn(signInRequest)
            .addOnSuccessListener { result ->
                try {
                    onSignInRequired?.invoke(result.pendingIntent.intentSender)
                } catch (e: IntentSender.SendIntentException) {
                    Log.e("FirebaseAuth", "Couldn't start One Tap UI: ${e.localizedMessage}")
                }
            }
            .addOnFailureListener { e ->
                // No saved credentials found. Launch the One Tap sign-up flow, or
                // do nothing and continue presenting the signed-out UI.
                e.localizedMessage?.let { Log.d("FirebaseAuth", it) }
                displayGoogleUISignIn()
            }


    }

    fun signIn(requestCode: Int, data: Intent?) {
        if (requestCode == REQ_ONE_TAP) {
            try {
                val credential = oneTapClient.getSignInCredentialFromIntent(data)
                val idToken = credential.googleIdToken
                when {
                    idToken != null -> {
                        // Use the ID token to authenticate with your backend.
                        Log.d("FirebaseAuth", "Got ID token.")
                        firebaseAuthWithGoogle(idToken)
                    }
                    else -> {
                        // No ID token or password.
                        Log.d("FirebaseAuth", "No ID token or password!")
                    }
                }
            } catch (e: Exception) {
                Log.d("FirebaseAuth", "Exception in signIn", e)
            }
        }
    }

    fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign-in success, update the user info
                    val user = auth.currentUser
                    if (user != null) {
                        _state.update { currentState ->
                            currentState.copy(
                                currentUser = user,
                                loggedIn = true,
                                userId = user.uid
                            )
                        }
                        Log.d("FirebaseAuth", "state is: ${state.value.loggedIn} and userID is ${state.value.userId}")
                    } else {
                        Log.w("FirebaseAuth", "Sign-in successful but the user is null")
                    }
                } else {
                    // Sign-in fails
                    Log.w("FirebaseAuth", "signInWithCredential:failure", task.exception)
                }
            }
    }

    fun checkUserCreated(callback: (Boolean) -> Unit) {
        if (auth.currentUser?.uid != null) {
            database.getReference("users").child(auth.currentUser!!.uid).child("firstName")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            Log.i("FirebaseAuth", "User has already been created, value checked: firstname = ${snapshot.value}")
                            _state.update { currentState ->
                                currentState.copy(
                                    userCreated = true,
                                )
                            }
                            callback(true)
                        } else {
                            _state.update { currentState ->
                                currentState.copy(
                                    userCreated = false,
                                )
                            }
                            Log.i("FirebaseAuth", "User has not been created yet")
                            callback(false)
                        }

                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.i(
                            "FirebaseAuth",
                            "An unexpected error occurred when checking if the user has been created"
                        )
                        _state.update { currentState ->
                            currentState.copy(
                                userCreated = false,
                            )
                        }
                        callback(false)
                    }
                })
        }
        else {
            Log.i("FirebaseAuth", "User has not been created yet while no user present at all")
            _state.update { currentState ->
                currentState.copy(userCreated = false)
            }
            callback(false)
        }
    }



    fun signOut() {
        auth.signOut()
        if (auth.currentUser == null) {
            Log.d("FirebaseAuth", "Signed out successfully")
            _state.update { currentState ->
                currentState.copy(
                    currentUser = null,
                    loggedIn = false,
                    userCreated = false,
                    userId = "",
                )
            }
        }
        else {
            Log.w("FirebaseAuth", "Signed out unsuccessfully")
        }
    }

    suspend fun createUser(email: String, password: String): Boolean {
        // Create a new coroutine scope
        return withContext(Dispatchers.IO) {
            try {
                val result = auth.createUserWithEmailAndPassword(email, password).await()
                if (result.user != null) {
                    Log.d("FirebaseAuth", "User created with email")
                    true // User successfully created
                } else {
                    Log.w("FirebaseAuth", "User was not successfully created, no user returned")
                    false
                }
            } catch (e: Exception) {
                Log.w("FirebaseAuth", "User was not successfully created", e)
                Toast.makeText(
                    context,
                    "Authentication failed: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
                false // User creation failed
            }
        }
    }

    fun signInUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("FirebaseAuth", "Sign in successful")
                    _state.update { currentState ->
                        currentState.copy(currentUser = auth.currentUser, loggedIn = true, userId = auth.currentUser!!.uid)
                    }
                    Log.d("FirebaseAuth", "State: ${state.value.currentUser}, ${state.value.loggedIn}")
                } else {
                    Log.w("FirebaseAuth", "Sign in unsuccessful")
                    Toast.makeText(
                        context,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    companion object {
        // Unique request code
        const val REQ_ONE_TAP = 1001
    }


}