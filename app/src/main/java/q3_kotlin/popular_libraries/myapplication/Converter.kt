package q3_kotlin.popular_libraries.myapplication

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import q3_kotlin.popular_libraries.myapplication.model.Image
import q3_kotlin.popular_libraries.myapplication.model.ImageConverter
import java.io.File
import java.io.FileOutputStream

class Converter(private val context: Context?) : ImageConverter {
    override fun convert(image: Image): Completable = Completable.fromAction {
        context.let { context ->
            try {
                Thread.sleep(3000)
            } catch (e: InterruptedException) {
                return@let
            }

            val newFile = File(context?.getExternalFilesDir(null), "myNewImage.png")
            val stream = FileOutputStream(newFile)
            image.data?.let { BitmapFactory.decodeByteArray(image.data, 0, it.size) }
                ?.compress(Bitmap.CompressFormat.PNG, 100, stream)
        }

    }.subscribeOn(Schedulers.io())
}