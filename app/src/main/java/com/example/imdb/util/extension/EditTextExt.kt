package com.example.imdb.util.extension

import android.text.method.HideReturnsTransformationMethod
import android.widget.EditText
import com.example.imdb.util.general.PasswordTransformationMethodWithDelay

fun EditText.clear() = setText("")

fun EditText.showPassword() {
    this.transformationMethod = PasswordTransformationMethodWithDelay()
}

fun EditText.hidePassword() {
    this.transformationMethod = HideReturnsTransformationMethod.getInstance()
}