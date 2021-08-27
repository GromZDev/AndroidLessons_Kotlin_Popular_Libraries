package q3_kotlin.popular_libraries.myapplication.retrofit

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import q3_kotlin.popular_libraries.myapplication.BuildConfig
import q3_kotlin.popular_libraries.myapplication.model.current.CurrentMovie
import q3_kotlin.popular_libraries.myapplication.model.current.CurrentMovieRepo

class RetrofitCurrentFilmRepo(
    private val api: CurrentMovieApi
) : CurrentMovieRepo {

    private val key: String = BuildConfig.FILM_API_KEY

    override fun getCurrentMovie(movieId: Long): Single<CurrentMovie> =
        api.getCurrentMovie(movieId, key, "ru")
            .subscribeOn(Schedulers.io())
}