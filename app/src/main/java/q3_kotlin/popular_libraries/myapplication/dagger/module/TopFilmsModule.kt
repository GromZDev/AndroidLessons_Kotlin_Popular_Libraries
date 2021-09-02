package q3_kotlin.popular_libraries.myapplication.dagger.module

import dagger.Module
import dagger.Provides
import q3_kotlin.popular_libraries.myapplication.model.topRated.TopRatedFilmsRepo
import q3_kotlin.popular_libraries.myapplication.retrofit.topRated.RetrofitTopRatedFilmsRepo
import q3_kotlin.popular_libraries.myapplication.retrofit.topRated.TopRatedMovieApi

/** Нужен для инъекции в TopRatedFilmsPresenter! */
@Module
class TopFilmsModule {


    @Provides
    fun topRatedMovies(
        api: TopRatedMovieApi
    ): TopRatedFilmsRepo = RetrofitTopRatedFilmsRepo(api)
}