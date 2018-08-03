package ru.ltcnt.basher.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*
import ru.ltcnt.basher.R
import ru.ltcnt.basher.app.BasherApplication
import ru.ltcnt.basher.data.models.BashPost
import ru.ltcnt.basher.domain.ApiRepositoryImpl
import ru.ltcnt.basher.utils.androidAsync
import javax.inject.Inject

interface MainView: MvpView{
    fun showLoading()
    fun hideLoading()
    fun showData(data: ArrayList<BashPost>)
    fun clearData()
}

class MainActivity : MvpAppCompatActivity(), MainView {
    @InjectPresenter
    lateinit var presenter: MainPresenter

    private val adapter: PostListAdapter = PostListAdapter()

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

    override fun showData(data: ArrayList<BashPost>) {
        adapter.setData(data)
    }

    override fun clearData() {
        adapter.clearData()
    }
}
