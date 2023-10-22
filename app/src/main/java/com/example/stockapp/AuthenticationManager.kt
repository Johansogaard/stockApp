
import android.util.Log
import com.example.stockapp.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object AuthenticationManager {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
   // private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference.child("users")
    val databaseUrl = "https://stockapp-26f58-default-rtdb.europe-west1.firebasedatabase.app/"
    val database = Firebase.database(databaseUrl)
    val myRef = database.getReference("users")

    fun signUp(email: String, password: String, username: String, callback: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = getCurrentUser()?.uid
                    Log.i("Tag","id = $userId")
                    userId?.let {
                        val user = User(email, username)
                       myRef.child(userId.toString()).setValue(user)
                    }
                    callback(true, null)
                } else {
                    callback(false, task.exception?.message)
                }
            }
        /*
        Toast.makeText(context,"email: $email username: $username", Toast.LENGTH_SHORT).show()
        val newUser = User(email,username)
        Toast.makeText(context,"userid:" + authManager.getCurrentUser().toString(), Toast.LENGTH_SHORT).show()
        myRef.child(authManager.getCurrentUser().toString()).setValue(newUser).addOnSuccessListener {
            Toast.makeText(context,"Success", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(context,"failed", Toast.LENGTH_SHORT).show()
        }*/
    }

    fun signIn(email: String, password: String, callback: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, null)
                } else {
                    callback(false, task.exception?.message)
                }
            }
    }

    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    fun signOut() {
        auth.signOut()
    }
}
