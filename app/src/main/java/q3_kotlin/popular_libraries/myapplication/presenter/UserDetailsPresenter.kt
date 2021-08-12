package q3_kotlin.popular_libraries.myapplication.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import q3_kotlin.popular_libraries.myapplication.model.GithubUser
import q3_kotlin.popular_libraries.myapplication.view.UserView

class UserDetailsPresenter(
    private val router: Router,
    private val userModel: GithubUser
) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        //   viewState.showUserLogin(userModel)
        userModel.login?.let { viewState.showUserLogin(userModel) }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}