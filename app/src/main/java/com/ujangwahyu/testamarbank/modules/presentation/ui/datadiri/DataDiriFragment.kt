package com.ujangwahyu.testamarbank.modules.presentation.ui.datadiri

import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.ujangwahyu.testamarbank.R
import com.ujangwahyu.testamarbank.common.base.BaseFragment
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
            viewModel.submitDataDiri(
                navigation,
                binding,
                FormDataDiriParams(
                    etNationalId.getText(),
                    etFullName.getText(),
                    etBankAccountNo.getText(),
                    etEducation.getText(),
                    etDateOfBirth.getText(),
                )
            )
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
            etNationalId.getEditText().doOnTextChanged { text, _, _, _ ->
                viewModel.validateNationalId(binding, text.toString())
            }

            etBankAccountNo.getEditText().doOnTextChanged { text, _, _, _ ->
                viewModel.validateBankAccountNo(binding, text.toString())
            }

            etEducation.getEditText().doOnTextChanged { text, _, _, _ ->
                viewModel.validateEducation(binding, text.toString())
            }

            etDateOfBirth.getEditText().doOnTextChanged { text, _, _, _ ->
                viewModel.validateDateOfBirth(binding, text.toString())
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