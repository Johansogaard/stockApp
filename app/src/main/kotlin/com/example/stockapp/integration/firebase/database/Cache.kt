package com.example.stockapp.integration.firebase.database

import android.content.Context
import android.util.Log
import kotlinx.serialization.json.*
import kotlinx.serialization.*


class Cache(context: Context) {

    val sharedPref = context.getSharedPreferences("BencherPreferences", Context.MODE_PRIVATE)
    val editor = sharedPref.edit()

    public val json = Json { ignoreUnknownKeys = true }

    val ONE_MINUTE_MILLIS = 60000
    val ONE_HOUR_MILLIS = 3600000

    inline fun <reified T> update(key: String, value: T) {
        Log.i("Cache", "Updating cache with item: $key")

        // Directly use reified type T to get the serializer
        val serializedValue = json.encodeToString(value)

        // Save the serialized value to SharedPreferences.
        editor.putString(key, serializedValue)
        editor.putLong(key + "_" + "timestamp", System.currentTimeMillis())
        editor.apply()
    }

    inline fun <reified T> retrieve(key: String): T? {
        Log.i("Cache", "Getting item $key from cache")

        val timestamp: Long = sharedPref.getLong(key + "_" + "timestamp", 0.toLong())
        if (System.currentTimeMillis() - timestamp >= ONE_HOUR_MILLIS) {
            Log.i("Cache", "Cache for $key was stale")
            return null
        }

        val value = sharedPref.getString(key, null) ?: return null
        return try {
            json.decodeFromString(value) // Deserialize directly using reified type T
        } catch (e: SerializationException) {
            Log.w("Cache", "Error deserializing object: ${e.message}")
            null
        }
    }

    fun clearCache() {
        editor.clear()
        editor.apply()
        Log.i("Cache", "Cache was cleared")
    }


    /*fun <T> update(key: String, value: T, itemType: Class<T>) {
        Log.i("Cache", "Updating cache with item: $key")
        @Suppress("UNCHECKED_CAST")
        if (itemType == String::class.java) {
            editor.putString(key, value as String)
        }
        else if (itemType == Int::class.java) {
            editor.putInt(key, value as Int)
        }
        else if (itemType == Boolean::class.java) {
            editor.putBoolean(key, value as Boolean)
        }
        else if (itemType == Long::class.java) {
            editor.putLong(key, value as Long)
        }
        else if (itemType == Float::class.java) {
            editor.putFloat(key, value as Float)
        }
        else if (itemType == Set::class.java) {
            try {
                editor.putStringSet(key, value as Set<String>)
            } catch (e : ClassCastException ) {
                Log.e("Cache", "Unsupported type: $itemType")
            }
        }
        editor.putLong(key + "_" + "timestamp", System.currentTimeMillis())
        editor.apply()
    }

    fun <T> retrieve(key: String, itemType: Class<T>) : T? {
        Log.i("Cache", "Getting item $key from cache")

        val timestamp: Long = sharedPref.getLong(key + "_" + "timestamp", 0.toLong())
        if (timestamp != 0.toLong()) {
            if (System.currentTimeMillis() - timestamp >= ONE_HOUR_MILLIS) {
                Log.i("Cache", "Cache for $key was stale")
                return null
            }
            else {
                Log.i("Cache", "Cache hit: time difference was: ${System.currentTimeMillis() - timestamp}")
            }
        }
        else {
            Log.i("Cache", "Timestamp was 0")
        }

        val result: T
        @Suppress("UNCHECKED_CAST")
        if (itemType == String::class.java) {
            result = sharedPref.getString(key, "") as T
            if (result as String == "") {
                return null
            }
        }
        else if (itemType == Int::class.java) {
            result = sharedPref.getInt(key, 0) as T
            if (result as Int == 0) {
                return null
            }
        }
        else if (itemType == Boolean::class.java) {
            result = sharedPref.getBoolean(key, false) as T
            if (!(result as Boolean)) {
                return null
            }
        }
        else if (itemType == Long::class.java) {
            result = sharedPref.getLong(key, 0) as T
            if (result as Long == 0.toLong()) {
                return null
            }
        }
        else if (itemType == Float::class.java) {
            result = sharedPref.getFloat(key, 0.0F) as T
            if (result as Float == 0.0F) {
                return null
            }
        }
        else if (itemType == Set::class.java) {
            val set = sharedPref.getStringSet(key, emptySet()) ?: emptySet<String>()
            if (set.isEmpty()) {
                return null
            } else {
                result = set as T
            }
        }
        else {
            return null
        }
        return result
    }

    fun updateString(key: String, value: String) {
        editor.putString(key, value)
    }

    fun updateInt(key: String, value: Int) {
        editor.putInt(key, value)
    }

    fun updateBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value)
    }

    fun updateLong(key: String, value: Long) {
        editor.putLong(key, value)
    }

    fun updateFloat(key: String, value: Float) {
        editor.putFloat(key, value)
    }

    fun updateStringSet(key: String, value: Set<String>) {
        editor.putStringSet(key, value)
    }

    fun retrieveString(key: String) {
        sharedPref.getString(key, "")
    }

    fun retrieveInt(key: String) : Int {
        return sharedPref.getInt(key, 0)
    }

    fun retrieveBoolean(key: String) : Boolean {
        return sharedPref.getBoolean(key, false)
    }

    fun retrieveLong(key: String) : Long {
        return sharedPref.getLong(key, 0)
    }

    fun retrieveFloat(key: String) : Float {
        return sharedPref.getFloat(key, 0.0F)
    }

    fun retrieveStringSet(key: String) : Set<String> {
        return sharedPref.getStringSet(key, setOf()) ?: setOf()
    }*/

}