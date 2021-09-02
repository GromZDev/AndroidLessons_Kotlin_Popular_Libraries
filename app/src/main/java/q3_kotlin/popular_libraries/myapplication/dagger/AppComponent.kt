package q3_kotlin.popular_libraries.myapplication.dagger

import dagger.Component
import q3_kotlin.popular_libraries.myapplication.dagger.module.*
import q3_kotlin.popular_libraries.myapplication.presenter.MainFilmPresenter
import q3_kotlin.popular_libraries.myapplication.presenter.cast.CastPresenter
import q3_kotlin.popular_libraries.myapplication.presenter.current.CurrentFilmPresenter
import q3_kotlin.popular_libraries.myapplication.presenter.popular.PopularFilmsPresenter
import q3_kotlin.popular_libraries.myapplication.presenter.topRated.TopRatedFilmsPresenter
import q3_kotlin.popular_libraries.myapplication.retrofit.popular.RetrofitPopularFilmsRepo
import q3_kotlin.popular_libraries.myapplication.view.MainActivity
import q3_kotlin.popular_libraries.myapplication.view.cast.CastFragment
import q3_kotlin.popular_libraries.myapplication.view.current.PopularCurrentFilmFragment

/** Тут мы перечисляем все модули, которые будем использовать
 * в приложении и инжектить */

@Component(
    modules = [
        CiceroneModule::class,
        ApiModule::class,
        ImageLoaderModule::class,
        SchedulerModule::class,
        PopularFilmsModule::class,
        TopFilmsModule::class
    ]
)
interface AppComponent {
    /** Куда будем делать inject */
    fun inject(popularFilmsPresenter: PopularFilmsPresenter)
    fun inject(currentFilmPresenter: CurrentFilmPresenter)
    fun inject(castPresenter: CastPresenter)
    fun inject(mainFilmPresenter: MainFilmPresenter)
    fun inject(mainActivity: MainActivity)
    fun inject(castFragment: CastFragment)
    fun inject(popularCurrentFilmFragment: PopularCurrentFilmFragment)
    fun inject(topRatedFilmsPresenter: TopRatedFilmsPresenter)
    fun inject(retrofitPopularFilmsRepo: RetrofitPopularFilmsRepo)
}