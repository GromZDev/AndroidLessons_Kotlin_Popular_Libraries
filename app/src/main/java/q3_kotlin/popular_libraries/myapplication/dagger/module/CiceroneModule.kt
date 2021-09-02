package q3_kotlin.popular_libraries.myapplication.dagger.module

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides

/** Тут методы, которые будут нам что-то возвращать. То, что
 * нам будет необходимо*/
@Module
class CiceroneModule {

    private val cicerone: Cicerone<Router> = Cicerone.create()

    @Provides /** Даггер найдет эту функцию */
    fun navigatorHolder(): NavigatorHolder = cicerone.getNavigatorHolder()

    @Provides
    fun router(): Router = cicerone.router
}