package com.ujangwahyu.testamarbank.modules.presentation.ui

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.ujangwahyu.testamarbank.R
import com.ujangwahyu.testamarbank.common.base.BaseFragment
import com.ujangwahyu.testamarbank.common.utils.PageType
import com.ujangwahyu.testamarbank.databinding.FragmentDataDiriBinding
import com.ujangwahyu.testamarbank.modules.presentation.viewmodel.FormViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DataDiriFragment: BaseFragment<FragmentDataDiriBinding>(FragmentDataDiriBinding::inflate) {

    private val viewModel: FormViewModel by activityViewModels()

    private val navigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_main)
    }

    override fun setupView(savedInstanceState: Bundle?) = with(binding) {
        btnSubmit.setOnClickListener {
            goToNextPage()
        }
    }

    override fun setupViewModel() {
        viewModel.setPosition(PageType.DATA_DIRI.name)
    }

    private fun goToNextPage() {
        navigation?.navigate(
            R.id.action_dataDiriFragment_to_alamatKtpFragment,
        )
    }
}