package com.shevart.rocketlaunches.util.ui

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

fun EditText.startEdit() {
    this.moveCursorToEnd()
    this.requestFocus()
    this.showKeyboard()
}

fun EditText.startEditByPredicate(predicate: Boolean) {
    if (predicate) {
        startEdit()
    }
}

fun EditText.moveCursorToEnd() {
    this.setSelection(this.text.length)
}

fun EditText.showKeyboard() {
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}