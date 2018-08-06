package ru.ltcnt.basher.data

import io.reactivex.Observable
import org.jsoup.Jsoup
import org.jsoup.nodes.TextNode
import ru.ltcnt.basher.data.models.BashPageResponse
import ru.ltcnt.basher.data.models.BashPost
import ru.ltcnt.basher.domain.repository.ApiRepositoryImpl

class ApiRepository: ApiRepositoryImpl {
    override fun getPage(pageNumber: Int): Observable<BashPageResponse> = Observable.create<BashPageResponse> { emitter ->
        val posts = arrayListOf<BashPost>()
        val doc = Jsoup.connect("http://bash.im/index/$pageNumber/").get()
        val rawPosts = doc.select("div.text")
        for (post in rawPosts) {
                posts.add(BashPost(post.childNodes()
                .filter { it is TextNode }
                .joinToString(separator = "\n") { (it as TextNode).text() })
            )
        }
        emitter.onNext(BashPageResponse(posts))
        emitter.onComplete()
    }


    override fun pagesCounter(): Observable<Int> = Observable.create { emitter ->
        val doc = Jsoup.connect("http://bash.im/").get()
        val inputField= doc.select("input.page")
        inputField.firstOrNull()?.let {
            emitter.onNext(it.attributes()["max"].toInt())
            emitter.onComplete()
        } ?: emitter.onError(NullPointerException("html element not found"))
    }
}