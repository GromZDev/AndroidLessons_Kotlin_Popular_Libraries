package q3_kotlin.popular_libraries.myapplication.presenter

import android.os.Bundle
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import q3_kotlin.popular_libraries.myapplication.AndroidScreens
import q3_kotlin.popular_libraries.myapplication.model.GithubUser
import q3_kotlin.popular_libraries.myapplication.model.GithubUsersRepo
import q3_kotlin.popular_libraries.myapplication.view.*

class UsersPresenter(private val usersRepo: GithubUsersRepo, private val router: Router) :
    MvpPresenter<UsersView>() {

    val screens = AndroidScreens() // Для перехода во фрагмент с описанием пользователя

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
        usersListPresenter.itemClickListener = { itemView ->

            //TODO: переход на экран пользователя c помощью router.navigateTo
            val currentUser = usersListPresenter.users[itemView.pos]
            val bundle = Bundle()
            bundle.putParcelable(UserDetailsFragment.BUNDLE_EXTRA, currentUser)
            router.navigateTo(screens.userDetails(bundle))

        }
    }

    private fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}