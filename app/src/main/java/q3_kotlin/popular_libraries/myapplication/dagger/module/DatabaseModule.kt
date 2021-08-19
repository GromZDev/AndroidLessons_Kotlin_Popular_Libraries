package q3_kotlin.popular_libraries.myapplication.dagger.module

import androidx.room.Room
import dagger.Module
import dagger.Provides
import q3_kotlin.popular_libraries.myapplication.App
import q3_kotlin.popular_libraries.myapplication.model.room.db.Database

@Module
class DatabaseModule {

    @Provides
    fun database(app: App) = Room.databaseBuilder(
        app,
        Database::class.java,
        Database.DB_NAME
    ).build()
}