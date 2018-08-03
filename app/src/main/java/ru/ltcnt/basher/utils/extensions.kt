package ru.ltcnt.basher.utils

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


fun <T> Observable<T>.androidAsync(): Observable<T> {
    return this.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
}