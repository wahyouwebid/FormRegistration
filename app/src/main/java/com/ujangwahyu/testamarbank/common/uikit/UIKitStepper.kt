package com.ujangwahyu.testamarbank.common.uikit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.ujangwahyu.testamarbank.databinding.UikitStepperBinding

class UIKitStepper(
    context: Context,
    attributeSet: AttributeSet? = null,
) : ConstraintLayout(context, attributeSet) {

    private val binding: UikitStepperBinding = UikitStepperBinding.inflate(
        LayoutInflater.from(context), this, true
    )

}