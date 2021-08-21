package q3_kotlin.popular_libraries.myapplication.retrofit

import io.reactivex.rxjava3.core.Single
import q3_kotlin.popular_libraries.myapplication.model.GithubSpecificUser
import q3_kotlin.popular_libraries.myapplication.model.GithubUser
import retrofit2.http.GET
import retrofit2.http.Url

interface IDataSource {
    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>

    @GET
    fun getRepositories(@Url url: String): Single<List<GithubSpecificUser>>
}