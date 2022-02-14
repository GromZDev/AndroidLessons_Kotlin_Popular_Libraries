package q3_kotlin.popular_libraries.myapplication.model.topRated

import io.reactivex.rxjava3.core.Single

interface TopRatedFilmsRepo {
    fun getTopRatedFilms(page: Int): Single<TopRatedResponse>
}