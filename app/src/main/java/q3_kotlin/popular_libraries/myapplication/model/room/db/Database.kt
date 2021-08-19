package q3_kotlin.popular_libraries.myapplication.model.room.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import q3_kotlin.popular_libraries.myapplication.model.dao.RepositoryDao
import q3_kotlin.popular_libraries.myapplication.model.dao.UserDao
import q3_kotlin.popular_libraries.myapplication.model.room.RoomGithubRepository
import q3_kotlin.popular_libraries.myapplication.model.room.RoomGithubUser

@androidx.room.Database(
    entities = [
        RoomGithubUser::class,
        RoomGithubRepository::class
    ], version = 1
)

abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    companion object {
        const val DB_NAME = "database.db"

        private var instance: Database? = null

        fun getInstance() = instance ?: throw IllegalStateException("Error db! Not created")
        fun create(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    Database::class.java,
                    DB_NAME
                ).build()
            }
        }
    }
}