package com.bael.dads.library.presentation.widget.listener

import android.text.Editable
import android.text.TextWatcher

/**
 * Created by ErickSumargo on 01/01/21.
 */

class OnTextChangedListener(
    private val callback: (String) -> Unit
) : TextWatcher {

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(editor: Editable?) {
        callback(editor?.toString().orEmpty())
    }
}
