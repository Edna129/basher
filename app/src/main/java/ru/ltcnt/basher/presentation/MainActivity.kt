package ru.ltcnt.basher.presentation

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*
import ru.ltcnt.basher.R
import ru.ltcnt.basher.app.BasherApplication
import ru.ltcnt.basher.domain.viewModels.BashPostView
import ru.ltcnt.basher.presentation.base.BaseView

interface MainView: BaseView{
    fun showLoading()
    fun hideLoading()
    fun showData(data: ArrayList<BashPostView>)
    fun clearData()
}

class MainActivity : MvpAppCompatActivity(), MainView {
    @InjectPresenter
    lateinit var presenter: MainPresenter

    private val adapter: PostListAdapter = PostListAdapter(onScrollToBottom = {
        presenter.onScrollToBottom()
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BasherApplication.appComponent.inject(this)
        setContentView(R.layout.activity_main)

        postsList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        postsList.adapter = adapter
        postListSwipe.setOnRefreshListener {
            presenter.loadPosts()
        }
    }

    override fun showLoading() {
        postListSwipe?.isRefreshing = true
    }

    override fun hideLoading() {
        postListSwipe?.isRefreshing = false
    }

    override fun showData(data: ArrayList<BashPostView>) {
        adapter.setData(data)
    }

    override fun clearData() {
        adapter.clearData()
    }

    override fun showError(text: String?) {
        Toast.makeText(this, text?: getString(R.string.some_error), Toast.LENGTH_SHORT)
            .show()
    }
}
