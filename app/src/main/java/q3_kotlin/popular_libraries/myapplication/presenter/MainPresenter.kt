package q3_kotlin.popular_libraries.myapplication.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import q3_kotlin.popular_libraries.myapplication.view.MainView
import q3_kotlin.popular_libraries.myapplication.nav.UsersScreen
import javax.inject.Inject

class MainPresenter :
    MvpPresenter<MainView>() {

    @Inject lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(UsersScreen.create())
    }

    fun backClicked() = router.exit()

}
