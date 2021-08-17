package q3_kotlin.popular_libraries.myapplication.model.room.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import q3_kotlin.popular_libraries.myapplication.model.GithubUser
import q3_kotlin.popular_libraries.myapplication.model.room.RoomGithubUser
import q3_kotlin.popular_libraries.myapplication.model.room.db.Database

class RoomUsersCache(private val db: Database) : UsersCache {
    override fun getUsersData(): Single<List<GithubUser>> = Single.fromCallable {
        db.userDao.getAll().map { roomUser ->
            GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
        }
    }

    override fun putUsersData(users: List<GithubUser>): Completable = Completable.fromAction {
        val roomUsers = users.map { user ->
            RoomGithubUser(
                user.id, user.login, user.avatarUrl ?: "", user.reposUrl ?: ""
            )
        }
        db.userDao.insert(roomUsers)
    }.subscribeOn(Schedulers.io())
}