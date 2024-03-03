package com.example.bbsuestc.widget

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import android.view.inputmethod.InputConnectionWrapper

class DeletableEditText : EmojiEditText {
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    )

    constructor(context: Context?) : super(context!!)

    override fun onCreateInputConnection(outAttrs: EditorInfo): InputConnection? {
        return DeleteInputConnection(
            super.onCreateInputConnection(outAttrs),
            true
        )
    }

    private inner class DeleteInputConnection(target: InputConnection?, mutable: Boolean) :
        InputConnectionWrapper(target, mutable) {
        override fun sendKeyEvent(event: KeyEvent): Boolean {
            return super.sendKeyEvent(event)
        }

        override fun deleteSurroundingText(beforeLength: Int, afterLength: Int): Boolean {
            return if (beforeLength == 1 && afterLength == 0) {
                (sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_DEL
                    )
                )
                        && sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_UP,
                        KeyEvent.KEYCODE_DEL
                    )
                ))
            } else super.deleteSurroundingText(beforeLength, afterLength)
        }
    }
}
