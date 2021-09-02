package q3_kotlin.popular_libraries.myapplication.dagger.module

import dagger.Module
import dagger.Provides
import q3_kotlin.popular_libraries.myapplication.model.popular.PopularFilmsRepo
import q3_kotlin.popular_libraries.myapplication.retrofit.popular.IDataSource
import q3_kotlin.popular_libraries.myapplication.retrofit.popular.RetrofitPopularFilmsRepo

/** Нужен для инъекции в TopRatedFilmsPresenter! */
@Module
class PopularFilmsModule {

    @Provides
    fun moviesRepo(
        api: IDataSource
    ): PopularFilmsRepo = RetrofitPopularFilmsRepo(api)
}