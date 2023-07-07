package com.ujangwahyu.testamarbank.modules.presentation.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.ujangwahyu.testamarbank.R
import com.ujangwahyu.testamarbank.common.base.BaseActivity
import com.ujangwahyu.testamarbank.databinding.ActivityMainBinding
import com.ujangwahyu.testamarbank.modules.presentation.viewmodel.FormViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private val viewModel: FormViewModel by viewModels()

    override fun setupView(savedInstanceState: Bundle?) {
        binding.toolbar.setToolbar(getString(R.string.title_toolbar))
    }

    override fun setupViewModel() = with(binding){
        viewModel.pagePosition.observe(this@MainActivity) {
            stepper.setStepper(it)
        }
    }
}