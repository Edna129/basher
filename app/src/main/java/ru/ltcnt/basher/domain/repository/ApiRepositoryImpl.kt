package ru.ltcnt.basher.domain.repository

import io.reactivex.Observable
import ru.ltcnt.basher.data.models.BashPageResponse

interface ApiRepositoryImpl {
    fun getPage(pageNumber: Int): Observable<BashPageResponse>
    fun pagesCounter(): Observable<Int>
}