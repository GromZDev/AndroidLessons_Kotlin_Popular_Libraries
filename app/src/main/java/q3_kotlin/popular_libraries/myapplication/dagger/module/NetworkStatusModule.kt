package q3_kotlin.popular_libraries.myapplication.dagger.module

import dagger.Module
import dagger.Provides
import q3_kotlin.popular_libraries.myapplication.App
import q3_kotlin.popular_libraries.myapplication.dagger.AppScope
import q3_kotlin.popular_libraries.myapplication.model.room.AndroidNetworkStatus
import q3_kotlin.popular_libraries.myapplication.model.room.INetworkStatus

@Module
class NetworkStatusModule {

    @AppScope
    @Provides
    /** Для его реализации создаём AppModule для получения контекста! */
    fun networkStatus(app: App): INetworkStatus = AndroidNetworkStatus(app)
}