package q3_kotlin.popular_libraries.myapplication.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import q3_kotlin.popular_libraries.myapplication.model.GithubUser
import q3_kotlin.popular_libraries.myapplication.model.IGithubUsersRepo
import q3_kotlin.popular_libraries.myapplication.nav.UserScreen
import q3_kotlin.popular_libraries.myapplication.view.UserItemView
import q3_kotlin.popular_libraries.myapplication.view.UsersView
import javax.inject.Inject

class UsersPresenter(
    private val uiScheduler: Scheduler,
    private val usersRepo: IGithubUsersRepo
) :
    MvpPresenter<UsersView>() {

    @Inject lateinit var router: Router

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login.let { view.setLogin(it) }
            user.avatarUrl?.let { view.loadImage(it) }
        }
    }

    val usersListPresenter = UsersListPresenter()

    /** Чтобы разом закрыть данные, на которые подписываемся: */
    private val disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {

        val users = usersRepo.getUsers()
            .observeOn(uiScheduler)
            .subscribe({ repos ->
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(repos)
                viewState.updateList()
            }, {
                println("Error: $(it.message)")
            })

        disposable.add(users)

        usersListPresenter.itemClickListener = { itemView ->

            val currentUser = usersListPresenter.users[itemView.pos]
            router.navigateTo(UserScreen(currentUser).create())

        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    /** В OnDestroy закрываем всё, на что подписываемся, и прекращаем
     * обработку данных из потока */
    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}