package q3_kotlin.popular_libraries.myapplication.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    val data: ByteArray?
) : Parcelable