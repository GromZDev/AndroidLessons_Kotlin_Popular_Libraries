package q3_kotlin.popular_libraries.myapplication.retrofit.topRated

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import q3_kotlin.popular_libraries.myapplication.BuildConfig
import q3_kotlin.popular_libraries.myapplication.model.topRated.TopRatedFilmsRepo
import q3_kotlin.popular_libraries.myapplication.model.topRated.TopRatedResponse

class RetrofitTopRatedFilmsRepo(private val api: TopRatedMovieApi) : TopRatedFilmsRepo {

    private val key: String = BuildConfig.FILM_API_KEY

    override fun getTopRatedFilms(): Single<TopRatedResponse> =
        api.getTopRatedMovies(key, "ru", 1)
            .subscribeOn(Schedulers.io())

}