package ru.ltcnt.basher.domain.interactor

import io.reactivex.Observable
import ru.ltcnt.basher.domain.repository.ApiRepositoryImpl
import ru.ltcnt.basher.domain.viewModels.BashPageView
import ru.ltcnt.basher.domain.viewModels.BashPostView
import javax.inject.Inject

data class GetBashPageCriteria(
        val pageNumber: Int
)

class GetBashPage @Inject constructor(
        private val repository: ApiRepositoryImpl
) : UseCase<GetBashPageCriteria, BashPageView>() {
    override fun buildObservable(criteria: GetBashPageCriteria): Observable<BashPageView> = repository.getPage(criteria.pageNumber).flatMap { response ->
        Observable.create<BashPageView> { emitter ->
            emitter.onNext(
                BashPageView(response.posts.map {
                    BashPostView(it.text)
                } as ArrayList<BashPostView>)
            )
            emitter.onComplete()
        }
    }
}