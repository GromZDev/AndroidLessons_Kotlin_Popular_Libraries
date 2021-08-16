package q3_kotlin.popular_libraries.myapplication.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import q3_kotlin.popular_libraries.myapplication.model.GithubUser

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView : MvpView {

    fun init()
    fun updateList()

    fun showUserInfo(user: GithubUser)

}