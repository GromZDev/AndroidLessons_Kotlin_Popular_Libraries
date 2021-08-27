package q3_kotlin.popular_libraries.myapplication.retrofit.popular

import io.reactivex.rxjava3.core.Single
import q3_kotlin.popular_libraries.myapplication.model.popular.GetMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface IDataSource {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") lang: String,
        @Query("page") page: Int
    ): Single<GetMoviesResponse>

}