package ru.ltcnt.basher

import io.reactivex.Observable
import ru.ltcnt.basher.data.models.BashPageResponse

interface ApiRepositoryImpl {
    fun getLastPage(): Observable<BashPageResponse>
}