package ru.ltcnt.basher.presentation.base

import com.arellomobile.mvp.MvpView

interface BaseView: MvpView {
    fun showError(text: String?)
}