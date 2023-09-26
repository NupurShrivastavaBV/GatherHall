package com.example.gatherhall.helper

import android.content.Context
import android.content.SharedPreferences

//Helper Class For Shared Preference
class PrefHelper (context: Context) {

    private val prefName = "shared_preference"
    private var sharedPref: SharedPreferences
    private val editor: SharedPreferences.Editor

    init {
        sharedPref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        editor = sharedPref.edit()
    }

    fun putBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value)
            .apply()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPref.getBoolean(key, false)
    }

}