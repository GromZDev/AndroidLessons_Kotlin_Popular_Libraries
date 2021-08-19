package q3_kotlin.popular_libraries.myapplication.dagger

import dagger.Component
import q3_kotlin.popular_libraries.myapplication.dagger.module.*
import q3_kotlin.popular_libraries.myapplication.presenter.MainPresenter
import q3_kotlin.popular_libraries.myapplication.presenter.UserDetailsPresenter
import q3_kotlin.popular_libraries.myapplication.presenter.UsersPresenter
import q3_kotlin.popular_libraries.myapplication.view.MainActivity

/** Тут мы перечисляем все модули, которые будем использовать
 * в приложении и инжектить */

@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        ApiModule::class,
        NetworkStatusModule::class,
        UsersRepoModule::class,
        CacheModule::class,
        DatabaseModule::class
    ]
)
interface AppComponent {
    /** Куда будем делать inject */
    fun inject(usersPresenter: UsersPresenter)
    fun inject(mainPresenter: MainPresenter)
    fun inject(mainActivity: MainActivity)
    fun inject(userDetailsPresenter: UserDetailsPresenter)


}