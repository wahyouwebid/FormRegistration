package com.ujangwahyu.testamarbank.common.uikit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.ujangwahyu.testamarbank.databinding.UikitToolbarBinding

class UIKitToolbar(
    context: Context,
    attributeSet: AttributeSet? = null,
) : ConstraintLayout(context, attributeSet) {

    private val binding: UikitToolbarBinding = UikitToolbarBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    fun setToolbar(res: String) {
        binding.tvToolbar.text = res
    }
}