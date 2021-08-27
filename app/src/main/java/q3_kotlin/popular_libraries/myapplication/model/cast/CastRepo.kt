package q3_kotlin.popular_libraries.myapplication.model.cast

import io.reactivex.rxjava3.core.Single

interface CastRepo {
    fun getPopularFilmsCast(movieId: Long): Single<Credits>
}