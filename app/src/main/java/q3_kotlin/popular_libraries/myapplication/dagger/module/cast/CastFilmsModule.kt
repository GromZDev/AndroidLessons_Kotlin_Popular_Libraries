package q3_kotlin.popular_libraries.myapplication.dagger.module.cast

import dagger.Module
import dagger.Provides
import q3_kotlin.popular_libraries.myapplication.model.cast.CastRepo
import q3_kotlin.popular_libraries.myapplication.model.current.CurrentMovieRepo
import q3_kotlin.popular_libraries.myapplication.retrofit.cast.CastApi
import q3_kotlin.popular_libraries.myapplication.retrofit.cast.RetrofitPopularFilmsCastRepo
import q3_kotlin.popular_libraries.myapplication.retrofit.current.CurrentMovieApi
import q3_kotlin.popular_libraries.myapplication.retrofit.current.RetrofitCurrentFilmRepo

@Module
class CastFilmsModule {

    @Provides
    fun provideCastRepo(
        api: CastApi
    ): CastRepo = RetrofitPopularFilmsCastRepo(api)
}