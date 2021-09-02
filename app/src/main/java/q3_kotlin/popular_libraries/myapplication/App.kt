package q3_kotlin.popular_libraries.myapplication

import android.app.Application
import q3_kotlin.popular_libraries.myapplication.dagger.AppComponent
import q3_kotlin.popular_libraries.myapplication.dagger.DaggerAppComponent

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        /** Конструируем сам компонент главный: */
        appComponent = DaggerAppComponent.builder()
            //  .appModule(AppModule(this))
            .build()
    }

}