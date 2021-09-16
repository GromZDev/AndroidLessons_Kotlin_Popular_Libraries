package q3_kotlin.popular_libraries.myapplication.dagger

import android.content.Context
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import io.reactivex.rxjava3.core.Scheduler
import q3_kotlin.popular_libraries.myapplication.App
import q3_kotlin.popular_libraries.myapplication.dagger.module.MainModule
import q3_kotlin.popular_libraries.myapplication.dagger.module.cast.ApiCastModule
import q3_kotlin.popular_libraries.myapplication.dagger.module.cast.CastFilmsModule
import q3_kotlin.popular_libraries.myapplication.dagger.module.current.ApiCurrentModule
import q3_kotlin.popular_libraries.myapplication.dagger.module.current.CurrentFilmsModule
import q3_kotlin.popular_libraries.myapplication.dagger.module.imageLoader.ImageLoaderModule
import q3_kotlin.popular_libraries.myapplication.dagger.module.popular.ApiModule
import q3_kotlin.popular_libraries.myapplication.dagger.module.popular.PopularFilmsModule
import q3_kotlin.popular_libraries.myapplication.dagger.module.topRated.ApiTopRatedModule
import q3_kotlin.popular_libraries.myapplication.dagger.module.topRated.TopRatedFilmsModule
import javax.inject.Singleton

/** AndroidInjectionModule Помогает инжектить внутрь компонентов андройд:
 * активити, сервисы, фрагменты, бродкаст-ресиверы...*/
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        MainModule::class,
        ApiModule::class,
        PopularFilmsModule::class,
        ApiTopRatedModule::class,
        TopRatedFilmsModule::class,
        ApiCurrentModule::class,
        CurrentFilmsModule::class,
        ApiCastModule::class,
        CastFilmsModule::class,
        ImageLoaderModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withRouter(router: Router): Builder

        @BindsInstance
        fun withNavigationHolder(navigatorHolder: NavigatorHolder): Builder

        @BindsInstance
        fun withSchedulers(schedulers: Scheduler): Builder

        fun build(): ApplicationComponent
    }
}