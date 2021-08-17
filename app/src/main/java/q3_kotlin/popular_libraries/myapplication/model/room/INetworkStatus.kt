package q3_kotlin.popular_libraries.myapplication.model.room

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.Observable
import java.util.*

interface INetworkStatus {
    fun isOnline(): Observable<Boolean>
    fun isOnlineSingle(): Single<Boolean>
}