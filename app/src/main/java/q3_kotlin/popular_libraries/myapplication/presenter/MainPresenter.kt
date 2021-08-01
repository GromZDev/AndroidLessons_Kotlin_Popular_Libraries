package q3_kotlin.popular_libraries.myapplication.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import q3_kotlin.popular_libraries.myapplication.IScreens
import q3_kotlin.popular_libraries.myapplication.model.GithubUser
import q3_kotlin.popular_libraries.myapplication.model.GithubUsersRepo
import q3_kotlin.popular_libraries.myapplication.view.MainView
import q3_kotlin.popular_libraries.myapplication.view.UserItemView

class MainPresenter(val router: Router, val screens: IScreens): MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }
    fun backClicked() {
        router.exit()
    }


//    class UsersListPresenter : IUserListPresenter {
//        val users = mutableListOf<GithubUser>()
//        override var itemClickListener: ((UserItemView) -> Unit)? = null
//        override fun getCount() = users.size
//        override fun bindView(view: UserItemView) {
//            val user = users[view.pos]
//            view.setLogin(user.login)
//        }
//    }
//    val usersListPresenter = UsersListPresenter()
//    override fun onFirstViewAttach() {
//        super.onFirstViewAttach()
//        viewState.init()
//        loadData()
//        usersListPresenter.itemClickListener = { itemView ->
////TODO: переход на экран пользователя
//        }
//    }
//    private fun loadData() {
//        val users = usersRepo.getUsers()
//        usersListPresenter.users.addAll(users)
//        viewState.updateList()
//    }
}
