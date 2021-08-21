package q3_kotlin.popular_libraries.myapplication.dagger.module

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler

@Module
class SchedulerModule {

    @Provides
    fun uiScheduler(): Scheduler = AndroidSchedulers.mainThread()

}