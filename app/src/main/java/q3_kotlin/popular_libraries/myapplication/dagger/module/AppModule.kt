package q3_kotlin.popular_libraries.myapplication.dagger.module

import dagger.Module
import dagger.Provides
import q3_kotlin.popular_libraries.myapplication.App

@Module
class AppModule(val app: App) {
    /** Нужен для реализации NetworkStatusModule! */

    @Provides
    fun app(): App = app
}