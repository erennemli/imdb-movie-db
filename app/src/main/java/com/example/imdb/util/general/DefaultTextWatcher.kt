package com.example.imdb.util.general

import android.text.Editable
import android.text.TextWatcher

abstract class DefaultTextWatcher : TextWatcher {

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        // no-op
    }

    override fun afterTextChanged(s: Editable) {
        // no-op
    }
}
