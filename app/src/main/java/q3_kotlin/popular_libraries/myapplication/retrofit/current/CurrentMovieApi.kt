package q3_kotlin.popular_libraries.myapplication.retrofit.current

import io.reactivex.rxjava3.core.Single
import q3_kotlin.popular_libraries.myapplication.model.current.CurrentMovie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CurrentMovieApi {
    @GET("movie/{movie_id}")
    fun getCurrentMovie(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String,
        @Query("language") lang: String
    ): Single<CurrentMovie>
}