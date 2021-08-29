package q3_kotlin.popular_libraries.myapplication.view.viewPager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import q3_kotlin.popular_libraries.myapplication.view.popular.PopularFilmsFragment
import q3_kotlin.popular_libraries.myapplication.view.topRatedFilms.TopRatedFilmsFragment

class ViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {

    private val fragments: LinkedHashMap<String, Fragment> = linkedMapOf(
        "Popular" to PopularFilmsFragment(), "TopRated" to TopRatedFilmsFragment()
    )

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> fragments["Popular"]!!
            1 -> fragments["TopRated"]!!
            else -> fragments["Popular"]!!
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Popular"
            1 -> "TopRated"
            else -> "Popular"
        }

    }
}