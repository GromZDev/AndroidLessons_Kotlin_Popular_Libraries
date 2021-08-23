package q3_kotlin.popular_libraries.myapplication.retrofit

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import q3_kotlin.popular_libraries.myapplication.BuildConfig
import q3_kotlin.popular_libraries.myapplication.model.GetMoviesResponse
import q3_kotlin.popular_libraries.myapplication.model.PopularFilmsRepo

class RetrofitPopularFilmsRepo(private val api: IDataSource) : PopularFilmsRepo {

    private val key: String = BuildConfig.FILM_API_KEY
    override fun getPopularFilms(): Single<GetMoviesResponse> =
        api.getPopularMovies(key, "ru", 1)
            .subscribeOn(Schedulers.io())

}