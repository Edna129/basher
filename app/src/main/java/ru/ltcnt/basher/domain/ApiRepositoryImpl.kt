package ru.ltcnt.basher.domain

import io.reactivex.Observable
import ru.ltcnt.basher.data.models.BashPageResponse

interface ApiRepositoryImpl {
    fun getLastPage(): Observable<BashPageResponse>
}