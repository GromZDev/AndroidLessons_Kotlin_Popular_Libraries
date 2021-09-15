package q3_kotlin.popular_libraries.myapplication.dagger.module.topRated

import dagger.Module
import dagger.Provides
import q3_kotlin.popular_libraries.myapplication.model.topRated.TopRatedFilmsRepo
import q3_kotlin.popular_libraries.myapplication.retrofit.topRated.RetrofitTopRatedFilmsRepo
import q3_kotlin.popular_libraries.myapplication.retrofit.topRated.TopRatedMovieApi

@Module
class TopRatedFilmsModule {

    @Provides
    fun provideTopRatedMovies(
        api: TopRatedMovieApi
    ): TopRatedFilmsRepo = RetrofitTopRatedFilmsRepo(api)
}