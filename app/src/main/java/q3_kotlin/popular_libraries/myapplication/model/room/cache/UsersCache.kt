package q3_kotlin.popular_libraries.myapplication.model.room.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import q3_kotlin.popular_libraries.myapplication.model.GithubUser

interface UsersCache {

    fun getUsersData(): Single<List<GithubUser>>
    fun putUsersData(users: List<GithubUser>): Completable

}