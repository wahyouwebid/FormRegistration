package com.ujangwahyu.testamarbank.modules.presentation.bottomsheet

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ujangwahyu.testamarbank.databinding.BottomsheetFormBinding
import com.ujangwahyu.testamarbank.modules.domain.model.EnumItem
import com.ujangwahyu.testamarbank.modules.presentation.adapter.FormEnumAdapter
import com.ujangwahyu.testamarbank.modules.presentation.viewmodel.FormViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HousingTypeBottomSheet: BottomSheetDialogFragment() {

    private lateinit var dialog: BottomSheetDialog

    private val viewModel: FormViewModel by activityViewModels()

    private val binding: BottomsheetFormBinding by lazy {
        BottomsheetFormBinding.inflate(layoutInflater)
    }

    private val productAdapter: FormEnumAdapter by lazy {
        FormEnumAdapter { item -> selectItem(item) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBehaviour()
        setupAdapter()
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.housingTypeList.observe(viewLifecycleOwner) {
            productAdapter.setData(it)
        }
    }

    private fun setupAdapter() {
        with(binding) {
            rvData.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = productAdapter
            }
        }
    }

    private fun selectItem(item: EnumItem) {
        viewModel.housingType.postValue(item)
        dismiss()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.behavior.skipCollapsed = true
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        return dialog
    }

    private fun setupBehaviour() {
        dialog.behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    dismiss()
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                //Not Implemented
            }

        })
    }

    override fun onStart() {
        super.onStart()
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }
}