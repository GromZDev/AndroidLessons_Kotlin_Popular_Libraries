package q3_kotlin.popular_libraries.myapplication.dagger

import dagger.Component
import dagger.android.ContributesAndroidInjector
import q3_kotlin.popular_libraries.myapplication.dagger.module.*
import q3_kotlin.popular_libraries.myapplication.presenter.MainFilmPresenter
import q3_kotlin.popular_libraries.myapplication.presenter.cast.CastPresenter
import q3_kotlin.popular_libraries.myapplication.presenter.current.CurrentFilmPresenter
import q3_kotlin.popular_libraries.myapplication.presenter.popular.PopularFilmsPresenter
import q3_kotlin.popular_libraries.myapplication.presenter.topRated.TopRatedFilmsPresenter
import q3_kotlin.popular_libraries.myapplication.retrofit.popular.RetrofitPopularFilmsRepo
import q3_kotlin.popular_libraries.myapplication.view.MainActivity
import q3_kotlin.popular_libraries.myapplication.view.cast.CastFragment
import q3_kotlin.popular_libraries.myapplication.view.cast.CastRVAdapter
import q3_kotlin.popular_libraries.myapplication.view.current.PopularCurrentFilmFragment
import q3_kotlin.popular_libraries.myapplication.view.popular.PopularFilmsRVAdapter
import q3_kotlin.popular_libraries.myapplication.view.topRatedFilms.TopRatedFilmsRVAdapter

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
    @ContributesAndroidInjector
    fun inject(popularFilmsPresenter: PopularFilmsPresenter)

    @ContributesAndroidInjector
    fun inject(currentFilmPresenter: CurrentFilmPresenter)

    @ContributesAndroidInjector
    fun inject(castPresenter: CastPresenter)

    @ContributesAndroidInjector
    fun inject(mainFilmPresenter: MainFilmPresenter)

    @ContributesAndroidInjector
    fun inject(mainActivity: MainActivity)

    @ContributesAndroidInjector
    fun inject(castFragment: CastFragment)

    @ContributesAndroidInjector
    fun inject(popularCurrentFilmFragment: PopularCurrentFilmFragment)

    @ContributesAndroidInjector
    fun inject(topRatedFilmsPresenter: TopRatedFilmsPresenter)

    @ContributesAndroidInjector
    fun inject(retrofitPopularFilmsRepo: RetrofitPopularFilmsRepo)

    @ContributesAndroidInjector
    fun inject(popularFilmsRVAdapter: PopularFilmsRVAdapter)

    @ContributesAndroidInjector
    fun inject(topRatedFilmsRVAdapter: TopRatedFilmsRVAdapter)

    @ContributesAndroidInjector
    fun inject(castRVAdapter: CastRVAdapter)
}