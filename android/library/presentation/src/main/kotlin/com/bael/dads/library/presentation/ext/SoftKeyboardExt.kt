package com.bael.dads.library.presentation.ext

import android.content.Context.INPUT_METHOD_SERVICE
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.SHOW_FORCED

/**
 * Created by ErickSumargo on 01/01/21.
 */

fun View.showSoftKeyboard() {
    val imm = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInputFromWindow(windowToken, SHOW_FORCED, 0)
}

fun View.hideSoftKeyboard() {
    val imm = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}