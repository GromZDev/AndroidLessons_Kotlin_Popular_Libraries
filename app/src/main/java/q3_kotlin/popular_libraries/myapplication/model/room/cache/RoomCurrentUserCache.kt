package q3_kotlin.popular_libraries.myapplication.model.room.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import q3_kotlin.popular_libraries.myapplication.model.GithubSpecificUser
import q3_kotlin.popular_libraries.myapplication.model.GithubUser
import q3_kotlin.popular_libraries.myapplication.model.room.RoomGithubRepository
import q3_kotlin.popular_libraries.myapplication.model.room.db.Database

class RoomCurrentUserCache(private val db: Database) : CurrentUserCache {

    override fun getUserForks(user: GithubUser): Single<List<GithubSpecificUser>> =
        Single.fromCallable {
            val roomUser = db.userDao.findByLogin(user.login)
                ?: throw RuntimeException("Нет такого пользователя в кэше")
            return@fromCallable db.repositoryDao.findForUser(roomUser.id)
                .map { GithubSpecificUser(it.id, it.name, it.forksCount) }
        }.subscribeOn(Schedulers.io())

    override fun putUserForks(
        user: GithubUser,
        repositories: List<GithubSpecificUser>
    ): Completable =
        Completable.fromAction {
            val roomUser = db.userDao.findByLogin(user.login)
                ?: throw RuntimeException("Нет такого пользователя в кэше")
            val roomRepos = repositories.map {
                RoomGithubRepository(it.id, it.name, it.forksCount, roomUser.id)
            }
            db.repositoryDao.insert(roomRepos)
        }.subscribeOn(Schedulers.io())

}