package ru.ltcnt.basher.domain.interactor

import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.observers.DisposableObserver

abstract class UseCase<in C, T> {
    private var mSubscription: Disposable = Disposables.empty()

    protected abstract fun buildObservable(criteria: C): Observable<T>

    fun execute(useCaseSubscriber: DisposableObserver<T>, criteria: C) {
        mSubscription = buildObservable(criteria)
                .subscribeWith(useCaseSubscriber)
    }

    fun execute(criteria: C) = buildObservable(criteria)

    fun unsubscribe() {
        if (!mSubscription.isDisposed) {
            mSubscription.dispose()
        }
    }
}