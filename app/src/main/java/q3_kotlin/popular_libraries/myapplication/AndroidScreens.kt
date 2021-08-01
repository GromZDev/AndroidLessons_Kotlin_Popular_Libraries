package q3_kotlin.popular_libraries.myapplication

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.FragmentScreen
import q3_kotlin.popular_libraries.myapplication.view.UserDetailsFragment
import q3_kotlin.popular_libraries.myapplication.view.UsersFragment

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun userDetails(bundle: Bundle) = FragmentScreen { UserDetailsFragment.newInstance(bundle) }
}
