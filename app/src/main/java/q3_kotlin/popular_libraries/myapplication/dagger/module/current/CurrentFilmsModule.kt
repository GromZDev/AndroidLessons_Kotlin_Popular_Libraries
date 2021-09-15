package q3_kotlin.popular_libraries.myapplication.dagger.module.current

import dagger.Module
import dagger.Provides
import q3_kotlin.popular_libraries.myapplication.model.current.CurrentMovieRepo
import q3_kotlin.popular_libraries.myapplication.retrofit.current.CurrentMovieApi
import q3_kotlin.popular_libraries.myapplication.retrofit.current.RetrofitCurrentFilmRepo

@Module
class CurrentFilmsModule {

    @Provides
    fun provideMovieRepo(
        api: CurrentMovieApi
    ): CurrentMovieRepo = RetrofitCurrentFilmRepo(api)
}