package com.ujangwahyu.testamarbank.modules.presentation.ui.ktp

import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.ujangwahyu.testamarbank.R
import com.ujangwahyu.testamarbank.common.base.BaseFragment
import com.ujangwahyu.testamarbank.common.utils.PageType
import com.ujangwahyu.testamarbank.common.utils.hide
import com.ujangwahyu.testamarbank.common.utils.show
import com.ujangwahyu.testamarbank.databinding.FragmentAlamatKtpBinding
import com.ujangwahyu.testamarbank.modules.domain.model.ProvinceItem
import com.ujangwahyu.testamarbank.modules.domain.state.FormResultState
import com.ujangwahyu.testamarbank.modules.presentation.bottomsheet.HousingTypeBottomSheet
import com.ujangwahyu.testamarbank.modules.presentation.bottomsheet.ProvinceBottomSheet
import com.ujangwahyu.testamarbank.modules.presentation.model.FormDataKtp
import com.ujangwahyu.testamarbank.modules.presentation.viewmodel.FormViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlamatKtpFragment: BaseFragment<FragmentAlamatKtpBinding>(FragmentAlamatKtpBinding::inflate) {

    private val viewModel: FormViewModel by activityViewModels()

    private val navigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_main)
    }

    override fun setupView(savedInstanceState: Bundle?) = with(binding) {
        frameHousingType.setOnClickListener {
            activity?.supportFragmentManager?.let { fragmentManaer ->
                HousingTypeBottomSheet().show(
                    fragmentManaer, PageType.ALAMAT_KTP.name
                )
            }
        }

        btnSubmit.setOnClickListener {
            viewModel.submitDataKtp(
                navigation,
                binding,
                FormDataKtp(
                    etDomicileAddress.getText(),
                    etHousingType.getText(),
                    etNo.getText(),
                    etProvince.getText(),
                )
            )
        }
        setupField()
    }

    override fun setupViewModel() = with(binding){
        viewModel.getProvince()
        viewModel.getHousingType()
        viewModel.setPosition(PageType.ALAMAT_KTP.name)

        viewModel.provinceState.observe(viewLifecycleOwner) {state ->
            when(state) {
                is FormResultState.Success -> setOnSuccess(state.data)
                is FormResultState.Loading -> setOnLoading(true)
                is FormResultState.Error -> setOnError(state.error)
            }
        }

        viewModel.housingType.observe(viewLifecycleOwner) {
            etHousingType.setText(it.name)
        }

        viewModel.province.observe(viewLifecycleOwner) {
            etProvince.setText(it.name)
        }

        viewModel.submitDataKtp.observe(viewLifecycleOwner) {
            etDomicileAddress.setText(it.domicileAddress)
            etHousingType.setText(it.housingType)
            etNo.setText(it.houseNo)
            etProvince.setText(it.province)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            if (it != null) {
                showError(it)
            }
        }
    }

    private fun setOnSuccess(data: List<ProvinceItem>) = with(binding) {
        setOnLoading(false)
        viewModel.provinceList.postValue(data)
        frameProvince.setOnClickListener {
            activity?.supportFragmentManager?.let { fragmentManaer ->
                ProvinceBottomSheet().show(
                    fragmentManaer, PageType.ALAMAT_KTP.name
                )
            }
        }
    }

    private fun setOnLoading(isLoading: Boolean) = with(binding) {
        if (isLoading) tvLoading.show() else tvLoading.hide()
    }

    private fun setOnError(error: Throwable) {
        setOnLoading(false)
        showError(error.message.toString())
    }

    private fun setupField() {
        with(binding) {
            etDomicileAddress.getEditText().doOnTextChanged { text, _, _, _ ->
                viewModel.validateDomicile(binding, text.toString())
            }

            etHousingType.getEditText().doOnTextChanged { text, _, _, _ ->
                viewModel.validateHousingType(binding, text.toString())
            }

            etNo.getEditText().doOnTextChanged { text, _, _, _ ->
                viewModel.validateHouseNumber(binding, text.toString())
            }

            etProvince.getEditText().doOnTextChanged { text, _, _, _ ->
                viewModel.validateProvince(binding, text.toString())
            }
        }
    }

    private fun showError(message: String) {
        val snackBar = Snackbar.make(binding.root, message, Snackbar.LENGTH_INDEFINITE)
        snackBar.setAction(getString(R.string.title_oke)) {
            snackBar.dismiss()
        }
        snackBar.show()
    }
}