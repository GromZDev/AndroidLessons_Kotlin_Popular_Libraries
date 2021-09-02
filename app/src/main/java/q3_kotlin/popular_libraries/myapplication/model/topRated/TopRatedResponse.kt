package q3_kotlin.popular_libraries.myapplication.model.topRated

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import q3_kotlin.popular_libraries.myapplication.model.popular.Movie

@Parcelize
data class TopRatedResponse(
    @SerializedName("page") @Expose val page: Int,
    @SerializedName("results") @Expose val topRatedMovies: List<Movie>,
    @SerializedName("total_pages") @Expose val pages: Int
) : Parcelable

