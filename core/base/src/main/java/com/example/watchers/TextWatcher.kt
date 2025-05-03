package com.example.watchers

import android.text.Editable
import android.text.TextWatcher

class TextWatcher(
    private val onTextChanged: (String) -> Unit,
    private val onError: () -> Unit
) : TextWatcher {

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        return
    }

    override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
        text?.let {
            if (text.isNotEmpty() && text.length > 3) {
                onTextChanged.invoke(text.toString())
            } else {
                onError.invoke()
            }
        } ?: run {
            onError.invoke()
        }
    }

    override fun afterTextChanged(p0: Editable?) {
        return
    }
}