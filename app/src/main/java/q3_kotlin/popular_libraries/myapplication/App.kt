package q3_kotlin.popular_libraries.myapplication

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import q3_kotlin.popular_libraries.myapplication.dagger.DaggerApplicationComponent

class App: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<App> =
        DaggerApplicationComponent
            .builder()
            .withContext(applicationContext)
            .apply {
                val cicerone = Cicerone.create()
                val schedulers: Scheduler = AndroidSchedulers.mainThread()
                withNavigationHolder(cicerone.getNavigatorHolder())
                withRouter(cicerone.router)
                withSchedulers(schedulers)
            }
            .build()

    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler {  }
    }
}