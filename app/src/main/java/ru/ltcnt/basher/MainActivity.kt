package ru.ltcnt.basher

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jsoup.Jsoup
import org.jsoup.nodes.TextNode


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        parse()

    }

    private fun parse() {
        Thread{

            val doc = Jsoup.connect("http://bash.im/").get()
            println(doc.title())
            val posts = doc.select("div.text")
            for (post in posts) {
                post.childNodes()
                    .filter { it is TextNode }
                    .forEach {
                        println((it as TextNode).text())
                    }
            }
        }.start()
    }
}
