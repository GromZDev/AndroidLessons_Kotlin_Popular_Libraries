package q3_kotlin.popular_libraries.myapplication.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import q3_kotlin.popular_libraries.myapplication.model.GithubSpecificUser
import q3_kotlin.popular_libraries.myapplication.model.GithubUser
import q3_kotlin.popular_libraries.myapplication.model.GithubSpecificUserRepo
import q3_kotlin.popular_libraries.myapplication.view.SpecificUserRepoItemView
import q3_kotlin.popular_libraries.myapplication.view.SpecificUserRVAdapter
import q3_kotlin.popular_libraries.myapplication.view.UserView
import javax.inject.Inject

class UserDetailsPresenter(
    private val uiScheduler: Scheduler,
    private val repo: GithubSpecificUserRepo,
    private val userModel: GithubUser
) : MvpPresenter<UserView>() {

    @Inject lateinit var router: Router

    class RepositoriesListPresenter : SpecificUserListPresenter {
        val repositories = mutableListOf<GithubSpecificUser>()
        override var itemClickListener: ((SpecificUserRepoItemView) -> Unit)? = null

        override fun getCount() = repositories.size

        override fun bindView(view: SpecificUserRVAdapter.ViewHolder) {
            val user = repositories[view.pos]
            user.forksCount.let { view.setForks(it) }
            user.name.let { view.setName(it) }
        }
    }

    val repositoriesListPresenter = RepositoriesListPresenter()

    private val disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {
        viewState.showUserInfo(userModel)
        val users =
            repo.getRepositories(userModel)
            .observeOn(uiScheduler)
            .subscribe({ repositories ->
                repositoriesListPresenter.repositories.clear()
                repositoriesListPresenter.repositories.addAll(repositories)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })

        disposable.add(users)


    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}