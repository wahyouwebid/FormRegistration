package com.ujangwahyu.testamarbank.modules.domain.interactor

import com.ujangwahyu.testamarbank.modules.domain.model.EnumItem
import com.ujangwahyu.testamarbank.modules.domain.repository.FormRepository
import com.ujangwahyu.testamarbank.modules.domain.state.FormResultState
import com.ujangwahyu.testamarbank.modules.domain.usecase.FormUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class FormInteractor(
    private val repository: FormRepository,
    private val disposable: CompositeDisposable,
): FormUseCase {

    override fun getProvince(callback: (data: FormResultState) -> Unit) {
        repository.getProvince()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map<FormResultState> { FormResultState.Success(it) }
            .onErrorReturn { FormResultState.Error(it) }
            .startWithItem(FormResultState.Loading)
            .subscribe(callback)
            .let { disposable.add(it) }

    }

    override fun getEducation(callback: (data: List<EnumItem>) -> Unit) {
        repository.getEducation()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(callback)
            .let { disposable.add(it) }
    }

    override fun getHousingType(callback: (data: List<EnumItem>) -> Unit) {
        repository.getHousingType()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(callback)
            .let { disposable.add(it) }
    }

    override fun clearDisposable() {
        disposable.clear()
    }


}