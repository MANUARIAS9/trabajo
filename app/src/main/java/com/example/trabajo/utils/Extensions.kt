package com.example.trabajo.utils

import android.app.Activity
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

inline fun <reified T> Activity.goToActivity() {
    val intent = Intent(applicationContext, T::class.java)
    startActivity(intent)
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}