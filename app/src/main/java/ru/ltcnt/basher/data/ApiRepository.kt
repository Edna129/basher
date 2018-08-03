package ru.ltcnt.basher.data

import io.reactivex.Observable
import org.jsoup.Jsoup
import org.jsoup.nodes.TextNode
import ru.ltcnt.basher.domain.ApiRepositoryImpl
import ru.ltcnt.basher.data.models.BashPageResponse
import ru.ltcnt.basher.data.models.BashPost

class ApiRepository: ApiRepositoryImpl {
    override fun getLastPage(): Observable<BashPageResponse> = Observable.create { emitter ->
        val posts = arrayListOf<BashPost>()
        val doc = Jsoup.connect("http://bash.im/").get()
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


}