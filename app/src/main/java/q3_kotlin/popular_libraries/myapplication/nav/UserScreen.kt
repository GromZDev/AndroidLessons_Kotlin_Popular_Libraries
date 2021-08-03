package q3_kotlin.popular_libraries.myapplication.nav

import com.github.terrakok.cicerone.androidx.FragmentScreen
import q3_kotlin.popular_libraries.myapplication.model.GithubUser
import q3_kotlin.popular_libraries.myapplication.view.UserDetailsFragment

class UserScreen(private val user: GithubUser) {

    fun create() = FragmentScreen { UserDetailsFragment.newInstance(user) }
}