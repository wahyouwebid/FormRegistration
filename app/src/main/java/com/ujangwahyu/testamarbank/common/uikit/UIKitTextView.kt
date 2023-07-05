package com.ujangwahyu.testamarbank.common.uikit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.ujangwahyu.testamarbank.R
import com.ujangwahyu.testamarbank.databinding.UikitTextViewBinding

class UIKitTextView(
    context: Context,
    attributeSet: AttributeSet? = null,
) : ConstraintLayout(context, attributeSet) {

    private val binding: UikitTextViewBinding = UikitTextViewBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    private var textLabel = ""

    init {
        attributeSet?.let {
            context.theme.obtainStyledAttributes(
                it,
                R.styleable.UIKitTextView,
                0,
                0
            ).apply {
                try {
                    getAttrs(attributeSet)
                } finally {
                    setView()
                    recycle()
                }
            }
        }
    }

    private fun getAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.UIKitTextView)
        typedArray.getString(R.styleable.UIKitTextView_text_value)?.let { setText(it) }
        typedArray.getString(R.styleable.UIKitTextView_text_label)?.let { setLabel(it) }
        typedArray.recycle()
    }

    private fun setView() {
        with(binding) {
            tvLabel.text = textLabel
        }
    }

    private fun setLabel(label: String) {
        this.textLabel = label
        setView()
    }

    fun setText(text: String?) {
        with(binding) {
            tvName.text = text
        }
    }
}