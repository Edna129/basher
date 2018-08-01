package ru.ltcnt.basher

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.htmlparser.Parser
import org.htmlparser.filters.TagNameFilter
import org.htmlparser.util.ParserException
import java.net.URL
import java.net.URLConnection
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.nodes.TextNode


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        parse()

    }

    fun parse() {
        Thread{

            val doc = Jsoup.connect("http://bash.im/").get()
            println(doc.title())
            val posts = doc.select("div.text")
            for (post in posts) {
                post.childNodes()
                        .filter { (it as? Element)?.tag().toString() != "br" }
                        .forEach {
                            println((it as TextNode).text())
                        }
                /*println(String.format("%s\n\t%s",
                        headline.attr("title"), headline.absUrl("href")))*/
            }
            /*try {


                val parser = Parser(URL("https://bash.im/").openConnection())
                parser.encoding = "utf-8"

                val atrb1 = TagNameFilter("text")
                val nodeList = parser.parse(atrb1)

                for (i in 0 until nodeList.size()) {
                    val node = nodeList.elementAt(i)
                    System.out.println(node.toHtml())
                }

            } catch (e: ParserException) {
                e.printStackTrace()
            }*/

        }.start()
    }
}
