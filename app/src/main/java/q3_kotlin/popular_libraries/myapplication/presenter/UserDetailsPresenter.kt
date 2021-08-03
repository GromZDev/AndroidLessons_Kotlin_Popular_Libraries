package q3_kotlin.popular_libraries.myapplication.presenter

import moxy.MvpPresenter
import q3_kotlin.popular_libraries.myapplication.model.GithubUser
import q3_kotlin.popular_libraries.myapplication.view.UserView

class UserDetailsPresenter(private val userModel: GithubUser) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showUserLogin(userModel)
    }
}