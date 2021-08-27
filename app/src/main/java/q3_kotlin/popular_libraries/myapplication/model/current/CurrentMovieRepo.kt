package q3_kotlin.popular_libraries.myapplication.model.current

import io.reactivex.rxjava3.core.Single

interface CurrentMovieRepo {
    fun getCurrentMovie(movieId: Long): Single<CurrentMovie>
}