package com.ujangwahyu.testamarbank.modules.presentation.ui.review

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import com.ujangwahyu.testamarbank.common.base.BaseFragment
import com.ujangwahyu.testamarbank.common.utils.PageType
import com.ujangwahyu.testamarbank.common.utils.checkEmpty
import com.ujangwahyu.testamarbank.databinding.FragmentReviewDataBinding
import com.ujangwahyu.testamarbank.modules.presentation.viewmodel.FormViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewDataFragment: BaseFragment<FragmentReviewDataBinding>(FragmentReviewDataBinding::inflate) {

    private val viewModel: FormViewModel by activityViewModels()

    override fun setupView(savedInstanceState: Bundle?) = with(binding){
        viewModel.submitDataDiri.observe(viewLifecycleOwner) { data ->
            tvNationalId.setText(data.nationalId)
            tvFullName.setText(data.fullName.checkEmpty())
            tvBankAccountNo.setText(data.bankAccountNo)
            tvEducation.setText(data.education)
            tvDateOfBirth.setText(data.dateOfBirth)
        }
        viewModel.submitDataKtp.observe(viewLifecycleOwner) { data ->
            tvDomicileAddress.setText(data.domicileAddress)
            tvHousingType.setText(data.housingType)
            tvHousingNo.setText(data.houseNo)
            tvProvince.setText(data.province)
        }
    }

    override fun setupViewModel() {
        viewModel.setPosition(PageType.REVIEW_DATA.name)
    }
}