package com.ujangwahyu.testamarbank.common.uikit

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.InputFilter
import android.text.InputType
import android.text.method.DigitsKeyListener
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.ujangwahyu.testamarbank.R
import com.ujangwahyu.testamarbank.common.utils.hide
import com.ujangwahyu.testamarbank.common.utils.show
import com.ujangwahyu.testamarbank.databinding.UikitEditTextBinding

class UIKitEditText(
    context: Context,
    attributeSet: AttributeSet? = null,
) : ConstraintLayout(context, attributeSet) {

    private val binding: UikitEditTextBinding = UikitEditTextBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    private var textHint = ""
    private var textLabel = ""
    private var endIconAlwaysShow = false
    private var onEndIconClick: () -> Unit = { binding.editText.setText("") }
    private var onInfoIconClick: () -> Unit = { }
    private var onFrameViewClick: () -> Unit = { }

    init {
        attributeSet?.let {
            context.theme.obtainStyledAttributes(
                it,
                R.styleable.UIKitEditText,
                0,
                0
            ).apply {
                try {
                    getAttrs(attributeSet)
                } finally {
                    setView()
                    setListener()
                    recycle()
                }
            }
        }
    }

    private fun getAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.UIKitEditText)
        typedArray.getString(R.styleable.UIKitEditText_field_text)?.let { setText(it) }
        val inlineText = typedArray.getString(R.styleable.UIKitEditText_field_text_inline)
        inlineText?.let { setInlineText(it) }
        if (typedArray.getBoolean(R.styleable.UIKitEditText_field_show_inline, false))
            showInline(inlineText) else hideInline()
        if (typedArray.getBoolean(R.styleable.UIKitEditText_field_info_icon, false))
            showInfoIcon() else hideInfoIcon()
        typedArray.getDrawable(R.styleable.UIKitEditText_field_info_icon_drawable)?.let {
            setInfoIconDrawable(it)
        }
        setEditTable(typedArray.getBoolean(R.styleable.UIKitEditText_field_edit_table, true))
        typedArray.getString(R.styleable.UIKitEditText_field_hint)?.let { setHint(it) }
        typedArray.getString(R.styleable.UIKitEditText_field_label)?.let { setLabel(it) }
        setMaxLength(typedArray.getInt(R.styleable.UIKitEditText_field_max_length, 0))
        val inputType = typedArray.getInt(R.styleable.UIKitEditText_field_input_type, InputType.TYPE_CLASS_TEXT)
        setInputType(inputType)
        typedArray.getString(R.styleable.UIKitEditText_field_digits)?.let { setFilters(it, inputType) }
        typedArray.recycle()
    }

    private fun setView() {
        with(binding) {
            tvLabel.text = textLabel
            if (editText.text.isNullOrEmpty() && !hasFocus()) {
                inputLayout.hint = textHint
                tvLabel.isVisible = false
            } else {
                inputLayout.hint = null
                tvLabel.isVisible = true
            }
        }
    }

    private fun setListener() {
        with(binding) {
            editText.doOnTextChanged { _, _, _, _ ->
                setView()
                ivClear.isVisible =
                    editText.hasFocus() && !editText.text.isNullOrBlank() || endIconAlwaysShow
            }
            editText.setOnFocusChangeListener { _, b ->
                inputLayout.apply {
                    ivClear.isVisible = b && !editText?.text.isNullOrBlank() || endIconAlwaysShow
                    when {
                        b -> {
                            hint = null
                            tvLabel.isVisible = true
                            showKeyboard()
                        }
                        !editText?.text.isNullOrEmpty() -> {
                            hint = null
                            tvLabel.isVisible = true
                        }
                        else -> {
                            hint = textHint
                            tvLabel.isVisible = false
                        }
                    }
                }
            }
            ivClear.setOnClickListener { onEndIconClick() }
            ivInfo.setOnClickListener { onInfoIconClick() }
            frameView.setOnClickListener { onFrameViewClick() }
        }
    }

    private fun setFilters(filters: String, inputType: Int = InputType.TYPE_CLASS_TEXT) {
        binding.editText.apply {
            keyListener = DigitsKeyListener.getInstance(filters)
            setRawInputType(inputType)
        }
    }

    private fun setEditTable(enable: Boolean) {
        binding.editText.isEnabled = enable
        binding.frameView.isVisible = !enable
    }

    private fun setHint(hint: String) {
        this.textHint = hint
        setView()
    }

    private fun setLabel(label: String) {
        this.textLabel = label
        setView()
    }

    fun setText(text: String?) {
        with(binding) {
            editText.setText(text)
        }
    }

    fun isError(errorMessage: String?){
        with(binding) {
            if (errorMessage.isNullOrEmpty()) {
                tvInline.text = context.getString(R.string.title_required)
                tvInline.setTextColor(context.getColor(R.color.colorTextPrimary))
                underline.setBackgroundColor(context.getColor(R.color.grey))
            } else {
                tvInline.text = errorMessage
                tvInline.setTextColor(context.getColor(R.color.red))
                underline.setBackgroundColor(context.getColor(R.color.red))
            }
        }
    }

    private fun setInlineText(string: String, isWarn: Boolean = false) {
        binding.apply {
            tvInline.text = string
            if (isWarn) {
                tvInline.setTextColor(context.getColor(R.color.red))
                underline.setBackgroundColor(context.getColor(R.color.red))
            } else {
                tvInline.setTextColor(context.getColor(R.color.colorTextPrimary))
                underline.setBackgroundColor(context.getColor(R.color.grey))
            }
        }
    }
    private fun showInline(string: String? = null, isWarn: Boolean = false) {
        if (string != null) setInlineText(string, isWarn)
        binding.tvInline.visibility = VISIBLE
    }

    private fun hideInline() {
        binding.tvInline.visibility = GONE
        binding.underline.setBackgroundColor(context.getColor(R.color.grey))
    }

    private fun setInputType(inputType: Int) {
        binding.editText.inputType = inputType
    }

    private fun setMaxLength(length: Int) {
        if (length > 0) binding.editText.filters = arrayOf<InputFilter>(
            InputFilter.LengthFilter(length)
        )
    }

    fun getText(): String {
        return binding.editText.text.toString()
    }

    fun getEditText(): EditText {
        return binding.editText
    }

    private fun showInfoIcon() {
        binding.ivInfo.show()
    }

    private fun hideInfoIcon() {
        binding.ivInfo.hide()
    }

    private fun setInfoIconDrawable(drawable: Drawable) {
        binding.ivInfo.setImageDrawable(drawable)
    }

    private fun showKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.editText, 0)
    }
}