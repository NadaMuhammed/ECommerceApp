package com.example.watchers

import android.text.Editable
import android.text.TextWatcher

class EmailTextWatcher(
    private val onTextChanged: (String) -> Unit,
    private val showError: () -> Unit
) : TextWatcher {

    companion object {
        val emailRegex = Regex(
            "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,6}\$"
        )
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        return
    }

    override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
        text?.let {
            if (text.isNotEmpty() && text.length > 3 && text.matches(emailRegex)) {
                onTextChanged.invoke(text.toString())
            } else {
                showError.invoke()
            }
        }
    }

    override fun afterTextChanged(p0: Editable?) {
        return
    }
}