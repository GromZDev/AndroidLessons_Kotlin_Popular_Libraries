package q3_kotlin.popular_libraries.myapplication.retrofit.topRated

import io.reactivex.rxjava3.core.Single
import q3_kotlin.popular_libraries.myapplication.model.topRated.TopRatedResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TopRatedMovieApi {
    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("language") lang: String,
        @Query("page") page: Int
    ): Single<TopRatedResponse>

}