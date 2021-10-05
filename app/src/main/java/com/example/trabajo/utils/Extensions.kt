package com.example.trabajo.utils

import android.app.Activity
import android.content.Intent

inline fun <reified T> Activity.goToActivity() {
    val intent = Intent(applicationContext, T::class.java)
    startActivity(intent)
}
