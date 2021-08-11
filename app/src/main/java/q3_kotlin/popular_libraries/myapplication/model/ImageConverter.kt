package q3_kotlin.popular_libraries.myapplication.model

import io.reactivex.rxjava3.core.Completable

interface ImageConverter {
    fun convert(image: Image): Completable
}