package q3_kotlin.popular_libraries.myapplication.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import q3_kotlin.popular_libraries.myapplication.model.GithubUser
import q3_kotlin.popular_libraries.myapplication.model.GithubUsersRepo
import q3_kotlin.popular_libraries.myapplication.nav.UserScreen
import q3_kotlin.popular_libraries.myapplication.view.UserItemView
import q3_kotlin.popular_libraries.myapplication.view.UsersView

class UsersPresenter(private val usersRepo: GithubUsersRepo, private val router: Router) :
    MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {
        val users = usersRepo.getUsers()

        usersListPresenter.users.addAll(users)
        usersListPresenter.itemClickListener = { itemView ->

            /** переход на экран пользователя c помощью router.navigateTo */
            router.navigateTo(UserScreen(users[itemView.pos]).create())

        }

        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}