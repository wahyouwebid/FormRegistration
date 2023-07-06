package com.ujangwahyu.testamarbank.modules.presentation.ui.datadiri

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.ujangwahyu.testamarbank.R
import com.ujangwahyu.testamarbank.common.base.BaseFragment
import com.ujangwahyu.testamarbank.common.utils.FormValidation.validateBankAccountNo
import com.ujangwahyu.testamarbank.common.utils.FormValidation.validateDateOfBirth
import com.ujangwahyu.testamarbank.common.utils.FormValidation.validateEducation
import com.ujangwahyu.testamarbank.common.utils.FormValidation.validateFullName
import com.ujangwahyu.testamarbank.common.utils.FormValidation.validateNationalId
import com.ujangwahyu.testamarbank.common.utils.PageType
import com.ujangwahyu.testamarbank.common.utils.showDatePicker
import com.ujangwahyu.testamarbank.databinding.FragmentDataDiriBinding
import com.ujangwahyu.testamarbank.modules.presentation.bottomsheet.EducationBottomSheet
import com.ujangwahyu.testamarbank.modules.presentation.model.FormDataDiriParams
import com.ujangwahyu.testamarbank.modules.presentation.viewmodel.FormViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DataDiriFragment: BaseFragment<FragmentDataDiriBinding>(FragmentDataDiriBinding::inflate) {

    private val viewModel: FormViewModel by activityViewModels()

    private val navigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_main)
    }

    override fun setupView(savedInstanceState: Bundle?) = with(binding) {
        frameEducation.setOnClickListener {
            childFragmentManager.let { fragmentManager ->
                EducationBottomSheet().show(
                    fragmentManager, PageType.DATA_DIRI.name
                )
            }
        }
        frameDateBrith.setOnClickListener {
            showDatePicker {
                etDateOfBirth.setText(it)
            }
        }
        btnSubmit.setOnClickListener {
            val data = FormDataDiriParams(
                etNationalId.getText(),
                etFullName.getText(),
                etBankAccountNo.getText(),
                etEducation.getText(),
                etDateOfBirth.getText(),
            )
            viewModel.submitDataDiri(data, binding, navigation)
        }
        setupField()
    }

    override fun setupViewModel() = with(binding) {
        viewModel.getEducation()
        viewModel.setPosition(PageType.DATA_DIRI.name)

        viewModel.education.observe(viewLifecycleOwner) {
            binding.etEducation.setText(it.name)
        }

        viewModel.submitDataDiri.observe(viewLifecycleOwner) {
            etNationalId.setText(it.nationalId)
            etFullName.setText(it.fullName)
            etBankAccountNo.setText(it.bankAccountNo)
            etEducation.setText(it.education)
            etDateOfBirth.setText(it.dateOfBirth)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            if (it != null) {
                showError(it)
            }
        }
    }

    private fun setupField() {
        with(binding) {
            etNationalId.validateNationalId()
            etBankAccountNo.validateBankAccountNo()
            etFullName.validateFullName()
            etEducation.validateEducation()
            etDateOfBirth.validateDateOfBirth()
        }
    }

    private fun showError(res: Int) {
        val snackBar = Snackbar.make(binding.root, getString(res), Snackbar.LENGTH_INDEFINITE)
        snackBar.setAction(getString(R.string.title_oke)) {
            snackBar.dismiss()
        }
        snackBar.show()
    }
}