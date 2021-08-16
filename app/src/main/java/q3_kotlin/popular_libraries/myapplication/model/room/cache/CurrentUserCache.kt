package q3_kotlin.popular_libraries.myapplication.model.room.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import q3_kotlin.popular_libraries.myapplication.model.GithubSpecificUser
import q3_kotlin.popular_libraries.myapplication.model.GithubUser

interface CurrentUserCache {

    fun getUserForks(user: GithubUser): Single<List<GithubSpecificUser>>
    fun putUserForks(user: GithubUser, repositories: List<GithubSpecificUser>): Completable

}