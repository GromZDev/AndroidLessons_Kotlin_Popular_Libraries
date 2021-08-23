package q3_kotlin.popular_libraries.myapplication.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import q3_kotlin.popular_libraries.myapplication.view.PopularFilmsFragment

object PopularFilmsScreen {

    fun create() = FragmentScreen { PopularFilmsFragment.newInstance() }

}