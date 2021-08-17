package q3_kotlin.popular_libraries.myapplication

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import q3_kotlin.popular_libraries.myapplication.model.room.db.Database

class App: Application() {
    companion object {
        lateinit var instance: App
    }

    /** Временно до даггера! */
    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router

    override fun onCreate() {
        super.onCreate()
        instance = this
        Database.create(this)

        /** Получаем всё, что хранится в БД*/
       // Database.getInstance().repositoryDao.getAll()
    }

}