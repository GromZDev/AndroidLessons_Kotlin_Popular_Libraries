package q3_kotlin.popular_libraries.myapplication.retrofit

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import q3_kotlin.popular_libraries.myapplication.model.GithubUser
import q3_kotlin.popular_libraries.myapplication.model.IGithubUsersRepo
import q3_kotlin.popular_libraries.myapplication.model.room.INetworkStatus
import q3_kotlin.popular_libraries.myapplication.model.room.cache.UsersCache

class RetrofitGithubUsersRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: UsersCache
) : IGithubUsersRepo {
    override fun getUsers(): Single<List<GithubUser>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getUsers()
                    .flatMap { users ->
                        /** Тут преобразуем Completable к Single: */
                        cache.putUsersData(users).toSingleDefault(users)
                    }
            } else {
                cache.getUsersData()

            }
        }.subscribeOn(Schedulers.io())
}

