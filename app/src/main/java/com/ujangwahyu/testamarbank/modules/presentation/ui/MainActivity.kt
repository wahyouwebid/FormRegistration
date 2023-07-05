package com.ujangwahyu.testamarbank.modules.presentation.ui

import android.os.Bundle
import com.ujangwahyu.testamarbank.R
import com.ujangwahyu.testamarbank.common.base.BaseActivity
import com.ujangwahyu.testamarbank.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun setupView(savedInstanceState: Bundle?) {
        binding.toolbar.setToolbar(getString(R.string.title_toolbar))
    }

}