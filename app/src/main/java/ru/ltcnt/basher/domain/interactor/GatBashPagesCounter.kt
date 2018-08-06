package ru.ltcnt.basher.domain.interactor

import io.reactivex.Observable
import ru.ltcnt.basher.domain.repository.ApiRepositoryImpl
import javax.inject.Inject

class GatBashPagesCounter @Inject constructor(
        private val repository: ApiRepositoryImpl
) : UseCase<Unit, Int>() {
    override fun buildObservable(criteria: Unit): Observable<Int> = repository.pagesCounter()
}