package q3_kotlin.popular_libraries.myapplication.nav

import com.github.terrakok.cicerone.androidx.FragmentScreen
import q3_kotlin.popular_libraries.myapplication.view.UsersFragment

object UsersScreen {

    fun create() = FragmentScreen { UsersFragment.newInstance() }

}