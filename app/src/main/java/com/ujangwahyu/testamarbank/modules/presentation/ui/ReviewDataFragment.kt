package com.ujangwahyu.testamarbank.modules.presentation.ui

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.ujangwahyu.testamarbank.common.base.BaseFragment
import com.ujangwahyu.testamarbank.common.utils.PageType
import com.ujangwahyu.testamarbank.databinding.FragmentReviewDataBinding
import com.ujangwahyu.testamarbank.modules.presentation.viewmodel.FormViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewDataFragment: BaseFragment<FragmentReviewDataBinding>(FragmentReviewDataBinding::inflate) {

    private val viewModel: FormViewModel by activityViewModels()

    override fun setupView(savedInstanceState: Bundle?) {

    }

    override fun setupViewModel() {
        viewModel.setPosition(PageType.REVIEW_DATA.name)
    }
}