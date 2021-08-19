package q3_kotlin.popular_libraries.myapplication.dagger

import dagger.Component
import q3_kotlin.popular_libraries.myapplication.dagger.module.CiceroneModule
import q3_kotlin.popular_libraries.myapplication.presenter.MainPresenter
import q3_kotlin.popular_libraries.myapplication.presenter.UserDetailsPresenter
import q3_kotlin.popular_libraries.myapplication.presenter.UsersPresenter
import q3_kotlin.popular_libraries.myapplication.view.MainActivity

/** Тут мы перечисляем все модули, которые будем использовать
 * в приложении и инжектить */

@Component(
    modules = [
        CiceroneModule::class
    ]
)
interface AppComponent {

    fun inject(usersPresenter: UsersPresenter) /** Куда будем делать inject */
    fun inject(mainPresenter: MainPresenter)
    fun inject(mainActivity: MainActivity)
    fun inject(userDetailsPresenter: UserDetailsPresenter)


}