package q3_kotlin.popular_libraries.myapplication.dagger.module.popular

import dagger.Module
import dagger.Provides
import q3_kotlin.popular_libraries.myapplication.model.popular.PopularFilmsRepo
import q3_kotlin.popular_libraries.myapplication.retrofit.popular.IDataSource
import q3_kotlin.popular_libraries.myapplication.retrofit.popular.RetrofitPopularFilmsRepo

@Module
class PopularFilmsModule {

    @Provides
    fun provideMoviesRepo(
        api: IDataSource
    ): PopularFilmsRepo = RetrofitPopularFilmsRepo(api)
}