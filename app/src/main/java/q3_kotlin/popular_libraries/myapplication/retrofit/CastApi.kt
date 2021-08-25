package q3_kotlin.popular_libraries.myapplication.retrofit

import io.reactivex.rxjava3.core.Single
import q3_kotlin.popular_libraries.myapplication.BuildConfig
import q3_kotlin.popular_libraries.myapplication.model.Credits
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CastApi {
    @GET("movie/{movie_id}/credits")
    fun getMovieCredits(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String = BuildConfig.FILM_API_KEY,
        @Query("language") lang: String
    ): Single<Credits>
}