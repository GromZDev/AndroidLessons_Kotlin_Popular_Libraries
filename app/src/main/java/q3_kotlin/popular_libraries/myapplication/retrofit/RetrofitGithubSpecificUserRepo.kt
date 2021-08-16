package q3_kotlin.popular_libraries.myapplication.retrofit

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import q3_kotlin.popular_libraries.myapplication.model.GithubSpecificUser
import q3_kotlin.popular_libraries.myapplication.model.GithubSpecificUserRepo
import q3_kotlin.popular_libraries.myapplication.model.GithubUser
import q3_kotlin.popular_libraries.myapplication.model.room.INetworkStatus
import q3_kotlin.popular_libraries.myapplication.model.room.cache.CurrentUserCache

class RetrofitGithubSpecificUserRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: CurrentUserCache
) : GithubSpecificUserRepo {

    override fun getRepositories(user: GithubUser): Single<List<GithubSpecificUser>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {

                user.reposUrl?.let { url ->
                    api.getRepositories(url)
                        .flatMap { repositories ->
                            cache.putUserForks(user, repositories).toSingleDefault(repositories)
                        }
                }
                    ?: Single.error<List<GithubSpecificUser>>(RuntimeException("Нет репозитория у него!"))
                        .subscribeOn(Schedulers.io())
            } else {
                cache.getUserForks(user)
            }
        }.subscribeOn(Schedulers.io())
}

