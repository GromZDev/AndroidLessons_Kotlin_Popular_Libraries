package q3_kotlin.popular_libraries.myapplication.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import q3_kotlin.popular_libraries.myapplication.navigation.ViewPagerScreen
import q3_kotlin.popular_libraries.myapplication.view.MainView

class MainFilmPresenter(private val router: Router) :
    MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(ViewPagerScreen.create())
    }

    fun backClicked() = router.exit()

}
