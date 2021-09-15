package q3_kotlin.popular_libraries.myapplication.dagger.module

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import q3_kotlin.popular_libraries.myapplication.view.MainActivity
import q3_kotlin.popular_libraries.myapplication.view.cast.CastFragment
import q3_kotlin.popular_libraries.myapplication.view.current.PopularCurrentFilmFragment
import q3_kotlin.popular_libraries.myapplication.view.popular.PopularFilmsFragment
import q3_kotlin.popular_libraries.myapplication.view.topRatedFilms.TopRatedFilmsFragment

@Module
interface MainModule {

    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun bindPopularFragment(): PopularFilmsFragment

    @ContributesAndroidInjector
    fun bindCurrentFragment(): PopularCurrentFilmFragment

    @ContributesAndroidInjector
    fun bindCastFragment(): CastFragment

    @ContributesAndroidInjector
    fun bindTopRatedFilmsFragment(): TopRatedFilmsFragment


}