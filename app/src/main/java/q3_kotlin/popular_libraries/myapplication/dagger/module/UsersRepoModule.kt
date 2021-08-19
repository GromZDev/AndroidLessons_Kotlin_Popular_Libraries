package q3_kotlin.popular_libraries.myapplication.dagger.module

import dagger.Module
import dagger.Provides
import q3_kotlin.popular_libraries.myapplication.model.IGithubUsersRepo
import q3_kotlin.popular_libraries.myapplication.model.room.INetworkStatus
import q3_kotlin.popular_libraries.myapplication.model.room.cache.UsersCache
import q3_kotlin.popular_libraries.myapplication.retrofit.IDataSource
import q3_kotlin.popular_libraries.myapplication.retrofit.RetrofitGithubUsersRepo

@Module
class UsersRepoModule {

    @Provides
    /** Нужен для инъекции в UsersPresenter!
    Если неоткуда взять данные, то переносим их в качестве аргументов: */
    fun usersRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: UsersCache,
    ): IGithubUsersRepo = RetrofitGithubUsersRepo(api, networkStatus, cache)
}