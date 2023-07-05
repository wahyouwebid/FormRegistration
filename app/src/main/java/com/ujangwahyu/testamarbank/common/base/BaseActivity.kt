package com.ujangwahyu.testamarbank.common.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T : ViewBinding>(private val bindingInflater: (layoutInflater: LayoutInflater) -> T) : AppCompatActivity() {

    private lateinit var _binding: T

    protected val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(binding.root)
        setupView(savedInstanceState)
        setupViewModel()
        setupData()
        setupListener()
    }

    abstract fun setupView(savedInstanceState: Bundle?)

    abstract fun setupViewModel()

    open fun setupData() {}

    open fun setupListener() {}
}