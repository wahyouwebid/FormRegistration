package com.ujangwahyu.testamarbank.modules.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(): ViewModel() {

    val pagePosition: MutableLiveData<String> by lazy {
        MutableLiveData()
    }

    fun setPosition(pageType: String) {
        pagePosition.postValue(pageType)
    }

}