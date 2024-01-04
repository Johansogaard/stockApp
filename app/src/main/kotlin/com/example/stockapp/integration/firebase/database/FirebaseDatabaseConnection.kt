package com.example.stockapp.integration.firebase.database

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.example.stockapp.integration.firebase.authentication.Authentication
import com.example.stockapp.integration.firebase.authentication.AuthenticationState
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Singleton
class FirebaseDatabaseConnection @Inject constructor(
    @ApplicationContext private val context: Context,
    private val authentication: Authentication,
) {

    val database = authentication.database
    val authState: StateFlow<AuthenticationState> = authentication.state

    var ready: Boolean = false

    val cache: Cache = Cache(context)

    init {
        CoroutineScope(Dispatchers.Default).launch {
            authentication.state.collect { authState ->
                if (authState.ready) {
                    val loggedInUser = authState.currentUser
                    Log.i("RealtimeDatabase", "User log in status: $loggedInUser")

                    // Perform additional actions based on user status
                    if (loggedInUser != null) {
                        ready = true
                        Log.i("RealtimeDatabase", "Database is ready")
                        return@collect
                    } else {
                        Log.i("RealtimeDatabase", "Database could not ready itself")
                        return@collect
                    }
                }
            }
        }
    }

    @SuppressLint("RestrictedApi")
    suspend inline fun <reified T> update(path: String, refPath: List<String>?, item: T, withCache: Boolean = true) : CompletableDeferred<Boolean> {
        return withContext(Dispatchers.IO) {
            val completion = CompletableDeferred<Boolean>()

            // Get the current user's ID
            val userId = authState.value.userId // Return if the user ID is null
            Log.i("FirebaseAuth", "User ID: $userId")
            // Start with the 'users' reference, then the user's specific path
            var userRef = database.getReference(path)

            if (refPath != null) {
                for (refItem in refPath) {
                    userRef = userRef.child(refItem)
                }
            }

            Log.i("FirebaseAuth", "Updating path: ${userRef.path}")

            // cache the item
            if (withCache) {
                cache.update(userRef.toString(), item)
            }

            Log.i("FirebaseAuth", "Preparing path in DB update function")

            // Update the data at the specified path
            userRef.setValue(item)
                .addOnSuccessListener {
                    Log.d("RealtimeDatabase", "Data successfully updated for user $userId")
                    completion.complete(true)
                }
                .addOnFailureListener { e ->
                    Log.w("RealtimeDatabase", "Error updating data for user $userId", e)
                }
            return@withContext completion
        }

    }

    suspend inline fun <reified T> retrieve(
        path: String,
        refPath: List<String>?,
        defaultValue: T,
        withCache: Boolean = true,
        limit: Int? = null,
        orderBy: String? = null,
        startAt: Double? = null,
        endAt: Double? = null,
        equalToString: String? = null,
    ) : T {
        return withContext(Dispatchers.IO) {
            // Get the current user's ID
            val userId = authState.value.userId // Return if the user ID is null
            Log.i("FirebaseAuth", "User ID: $userId")
            // Start with the 'users' reference, then the user's specific path
            var userRef = database.getReference(path)

            if (refPath != null) {
                for (refItem in refPath) {
                    userRef = userRef.child(refItem)
                }
            }

            return@withContext suspendCoroutine { cont ->

                var isResumed = false

                // try to get value from cache
                if (withCache) {
                    val cachedValue: T? = cache.retrieve(userRef.toString())
                    if (cachedValue != null) {
                        isResumed = true
                        cont.resume(cachedValue)
                        return@suspendCoroutine
                    }
                }

                val query: Query = database.getReference(path)

                if (limit != null) {
                    query.limitToFirst(limit)
                }

                if (orderBy != null) {
                    query.orderByChild(orderBy)
                }

                if (startAt != null) {
                    query.startAt(startAt)
                }

                if (endAt != null) {
                    query.endAt(endAt)
                }

                if (equalToString != null) {
                    query.equalTo(equalToString)
                }

                // Add a listener to read the data at our user's coins reference
                userRef.addListenerForSingleValueEvent (object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (!isResumed) {
                            val result: T? = dataSnapshot.getValue(object : GenericTypeIndicator<T>() { })
                            //Log.d(
                            //    "RealtimeDatabase",
                            //    "Database called by user ${authState.value.userId} provided: $result."
                            //)
                            // Cache the result
                            if (withCache) {
                                cache.update(userRef.toString(), result)
                            }
                            cont.resume(result ?: defaultValue)
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        if (!isResumed) {
                            Log.w(
                                "RealtimeDatabase",
                                "loadUserCoins:onCancelled",
                                databaseError.toException()
                            )
                            cont.resume(defaultValue)
                        }
                    }
                })
            }
        }
    }

    fun clearCache() {
        cache.clearCache()
    }

    /*suspend fun checkUserHasBeenCreated() {
        if (retrieve(listOf("firstname"), String.Companion::class.java) == null) {

        }
    }*/

}