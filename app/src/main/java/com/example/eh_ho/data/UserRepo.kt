package com.example.eh_ho.data

import android.content.Context
import android.content.Context.MODE_PRIVATE

const val PREFERENCES_SESSION = "session"
const val PREFERENCES_SESSION_USERNAME = "username"

object UserRepo {

    fun signIn(context: Context, username: String) {
        saveSession(context, username)
    }

    fun isLogged(context: Context): Boolean {
        val pref = context.getSharedPreferences(PREFERENCES_SESSION, MODE_PRIVATE)
        val user = pref.getString(PREFERENCES_SESSION_USERNAME, null)
        return user != null
    }

    private fun saveSession(context: Context,  username: String) {
        val pref = context.applicationContext.getSharedPreferences(PREFERENCES_SESSION, MODE_PRIVATE)
        pref.edit()
            .putString(PREFERENCES_SESSION_USERNAME, username)
            .apply()
    }

    fun logOut(context: Context) {
        val pref = context.applicationContext.getSharedPreferences(PREFERENCES_SESSION, MODE_PRIVATE)
        pref.edit()
            .remove(PREFERENCES_SESSION_USERNAME)
            .apply()
    }

}