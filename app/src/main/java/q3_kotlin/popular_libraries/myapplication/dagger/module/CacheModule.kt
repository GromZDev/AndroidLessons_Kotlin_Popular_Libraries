package q3_kotlin.popular_libraries.myapplication.dagger.module

import dagger.Module
import dagger.Provides
import q3_kotlin.popular_libraries.myapplication.dagger.AppScope
import q3_kotlin.popular_libraries.myapplication.model.room.cache.RoomUsersCache
import q3_kotlin.popular_libraries.myapplication.model.room.cache.UsersCache
import q3_kotlin.popular_libraries.myapplication.model.room.db.Database

@Module
class CacheModule {

    @AppScope
    @Provides
    /** Нужен для реализации UsersRepoModule. А для его реализации
     * создаём DatabaseModule, тк нужна бд! */
    fun usersCache(db: Database): UsersCache = RoomUsersCache(db)
}