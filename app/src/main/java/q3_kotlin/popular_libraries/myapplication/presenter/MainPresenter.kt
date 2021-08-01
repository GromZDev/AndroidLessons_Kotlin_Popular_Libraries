package q3_kotlin.popular_libraries.myapplication.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import q3_kotlin.popular_libraries.myapplication.IScreens
import q3_kotlin.popular_libraries.myapplication.view.MainView

class MainPresenter(private val router: Router, val screens: IScreens): MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }
    fun backClicked() {
        router.exit()
    }

}
