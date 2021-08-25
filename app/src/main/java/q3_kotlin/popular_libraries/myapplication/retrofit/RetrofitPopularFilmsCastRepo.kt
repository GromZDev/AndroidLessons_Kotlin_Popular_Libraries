package q3_kotlin.popular_libraries.myapplication.retrofit

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import q3_kotlin.popular_libraries.myapplication.BuildConfig
import q3_kotlin.popular_libraries.myapplication.model.CastRepo
import q3_kotlin.popular_libraries.myapplication.model.Credits

class RetrofitPopularFilmsCastRepo(
    private val api: CastApi
    /**   , private val movieId: Long */
) : CastRepo {

    private val key: String = BuildConfig.FILM_API_KEY

    override fun getPopularFilmsCast(movieId: Long): Single<Credits> =
        api.getMovieCredits(movieId, key, "ru")
            .subscribeOn(Schedulers.io())
}