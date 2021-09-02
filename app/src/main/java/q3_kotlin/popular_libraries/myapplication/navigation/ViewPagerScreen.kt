package q3_kotlin.popular_libraries.myapplication.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import q3_kotlin.popular_libraries.myapplication.view.viewPager.ViewPagerFragment

object ViewPagerScreen {

    fun create() = FragmentScreen { ViewPagerFragment.newInstance() }

}