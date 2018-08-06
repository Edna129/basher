package ru.ltcnt.basher.presentation


import com.arellomobile.mvp.InjectViewState
import ru.ltcnt.basher.app.BasherApplication
import ru.ltcnt.basher.domain.interactor.GatBashPagesCounter
import ru.ltcnt.basher.domain.interactor.GetBashPage
import ru.ltcnt.basher.domain.interactor.GetBashPageCriteria
import ru.ltcnt.basher.domain.viewModels.BashPostView
import ru.ltcnt.basher.presentation.base.BasePresenter
import ru.ltcnt.basher.utils.androidAsync
import javax.inject.Inject

@InjectViewState
class MainPresenter: BasePresenter<MainView>() {

    @Inject
    lateinit var getPagesCounter: GatBashPagesCounter
    @Inject
    lateinit var getBashPage: GetBashPage

    private val posts = arrayListOf<BashPostView>()
    private var currentPage = 0

    init {
        BasherApplication.appComponent.inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadPosts()
    }

    fun loadPosts(){
        viewState.showLoading()
        viewState.clearData()

        posts.clear()

        addSubscribation(getPagesCounter.execute(Unit).flatMap { pageCounter ->
            currentPage = pageCounter
            getBashPage.execute(GetBashPageCriteria(currentPage))
        }.compose {
            it.androidAsync()
        }.subscribe({
            posts.addAll(it.posts)
            viewState.showData(posts)
            viewState.hideLoading()
        },{
            it.printStackTrace()
            viewState.hideLoading()
            viewState.showError(it.message)
        }))
    }

    fun onScrollToBottom() {
        if (currentPage > 0){
            viewState.showLoading()
            addSubscribation(getBashPage.execute(GetBashPageCriteria(--currentPage)).compose {
                it.androidAsync()
            }.subscribe({
                posts.addAll(it.posts)
                viewState.showData(posts)
                viewState.hideLoading()
            },{
                it.printStackTrace()
                viewState.showError(it.message)
                viewState.hideLoading()
                currentPage++
            }))
        } else
            viewState.showError("Все")
    }

    fun onItemLongClick(post: BashPostView) {
        viewState.showError("Текст скопирован в буфер обмена")
    }
}