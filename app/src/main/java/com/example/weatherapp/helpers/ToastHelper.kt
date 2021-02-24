package com.example.weatherapp.helpers

import android.content.Context
import android.widget.Toast

// TODO would use this extension function for error handling on LookupFragment
fun Context.sendToast(message: String) {
    Toast.makeText(
        this,
        message,
        Toast.LENGTH_SHORT
    ).show()
}