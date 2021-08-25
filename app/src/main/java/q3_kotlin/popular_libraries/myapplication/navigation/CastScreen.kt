package q3_kotlin.popular_libraries.myapplication.navigation

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.FragmentScreen
import q3_kotlin.popular_libraries.myapplication.view.CastFragment

object CastScreen {

    fun create(bundle: Bundle) = FragmentScreen { CastFragment.newInstance(bundle) }

}