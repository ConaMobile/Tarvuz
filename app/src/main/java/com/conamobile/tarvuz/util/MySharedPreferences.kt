package com.conamobile.tarvuz.util

import android.content.Context

class MySharedPreferences(context: Context) {
    private val pref = context.getSharedPreferences("shared", Context.MODE_PRIVATE)

    fun isFirstLogin(isFirstLogin: Boolean) {
        val editor = pref.edit()
        editor.putBoolean("isFirstLogin", isFirstLogin)
        editor.apply()
    }

    fun getFirstLogin(): Boolean {
        return pref.getBoolean("isFirstLogin", true)
    }

    fun isUserName(isFirstLogin: String) {
        val editor = pref.edit()
        editor.putString("isUserName", isFirstLogin)
        editor.apply()
    }

    fun getUserName(): String? {
        return pref.getString("isUserName", null)
    }

    fun isUserPhone(isFirstLogin: String) {
        val editor = pref.edit()
        editor.putString("isUserPhone", isFirstLogin)
        editor.apply()
    }

    fun getUserPhone(): String? {
        return pref.getString("isUserPhone", null)
    }
}