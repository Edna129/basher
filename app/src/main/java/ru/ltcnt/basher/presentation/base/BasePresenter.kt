package ru.ltcnt.basher.presentation.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<view: MvpView>: MvpPresenter<view>() {
    private val compositeDisposable = CompositeDisposable()

    fun addSubscribation(observer: Disposable){
        compositeDisposable.add(observer)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}