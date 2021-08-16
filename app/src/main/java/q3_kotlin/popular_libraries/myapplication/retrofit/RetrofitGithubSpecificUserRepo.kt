package q3_kotlin.popular_libraries.myapplication.retrofit

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import q3_kotlin.popular_libraries.myapplication.model.GithubSpecificUser
import q3_kotlin.popular_libraries.myapplication.model.GithubUser
import q3_kotlin.popular_libraries.myapplication.model.GithubSpecificUserRepo
import java.lang.RuntimeException

class RetrofitGithubSpecificUserRepo(private val api: IDataSource) : GithubSpecificUserRepo {
    override fun getRepositories(user: GithubUser) = user.reposUrl?.let { url ->
         api.getRepositories(url).subscribeOn(Schedulers.io())
    } ?: Single.error<List<GithubSpecificUser>>(RuntimeException("Error!")).subscribeOn(Schedulers.io())
}