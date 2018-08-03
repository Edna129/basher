package ru.ltcnt.basher.presentation

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable
import ru.ltcnt.basher.app.BasherApplication
import ru.ltcnt.basher.domain.ApiRepositoryImpl
import ru.ltcnt.basher.utils.androidAsync
import javax.inject.Inject

@InjectViewState
class MainPresenter: MvpPresenter<MainView>() {

    @Inject
    lateinit var repository: ApiRepositoryImpl

    init {
        BasherApplication.appComponent.inject(this)
    }

    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadPosts()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    fun loadPosts(){
        viewState.showLoading()
        viewState.clearData()
        compositeDisposable.add(repository.getLastPage()
            .compose {
                it.androidAsync()
            }.subscribe({
                viewState.showData(it.posts)
                viewState.hideLoading()
            },{
                it.printStackTrace()
                viewState.hideLoading()
            })
        )
    }
}