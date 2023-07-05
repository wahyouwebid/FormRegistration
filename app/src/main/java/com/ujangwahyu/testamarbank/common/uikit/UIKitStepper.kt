package com.ujangwahyu.testamarbank.common.uikit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.ujangwahyu.testamarbank.R
import com.ujangwahyu.testamarbank.common.utils.PageType
import com.ujangwahyu.testamarbank.databinding.UikitStepperBinding

class UIKitStepper(
    context: Context,
    attributeSet: AttributeSet? = null,
) : ConstraintLayout(context, attributeSet) {

    private val binding: UikitStepperBinding = UikitStepperBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    fun setStepper(pageType: String) = with(binding){
        when(pageType) {
            PageType.DATA_DIRI.name -> {
                ivDataDiri.setImageResource(R.drawable.bg_circle_selected)
                ivAlamatKtp.setImageResource(R.drawable.bg_circle)
                ivReviewData.setImageResource(R.drawable.bg_circle)
                tvDataDiri.setTextColor(ContextCompat.getColor(context, R.color.colorAccent))
                tvAlamatKtp.setTextColor(ContextCompat.getColor(context, R.color.colorSecondary))
                tvReviewData.setTextColor(ContextCompat.getColor(context, R.color.colorSecondary))
            }

            PageType.ALAMAT_KTP.name -> {
                ivDataDiri.setImageResource(R.drawable.bg_circle_selected)
                ivAlamatKtp.setImageResource(R.drawable.bg_circle_selected)
                ivReviewData.setImageResource(R.drawable.bg_circle)
                tvDataDiri.setTextColor(ContextCompat.getColor(context, R.color.colorAccent))
                tvAlamatKtp.setTextColor(ContextCompat.getColor(context, R.color.colorAccent))
                tvReviewData.setTextColor(ContextCompat.getColor(context, R.color.colorSecondary))
            }

            else -> {
                ivDataDiri.setImageResource(R.drawable.bg_circle_selected)
                ivAlamatKtp.setImageResource(R.drawable.bg_circle_selected)
                ivReviewData.setImageResource(R.drawable.bg_circle_selected)
                tvDataDiri.setTextColor(ContextCompat.getColor(context, R.color.colorAccent))
                tvAlamatKtp.setTextColor(ContextCompat.getColor(context, R.color.colorAccent))
                tvReviewData.setTextColor(ContextCompat.getColor(context, R.color.colorAccent))
            }
        }
    }
}