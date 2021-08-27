package q3_kotlin.popular_libraries.myapplication.model.current

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrentMovie(
    @SerializedName("id") @Expose val id: Long,
    @SerializedName("title") @Expose val title: String,
    @SerializedName("budget") @Expose val budget: Int,
    @SerializedName("homepage") @Expose val homepage: String? = null,
    @SerializedName("overview") @Expose val overview: String? = null,
    @SerializedName("status") @Expose val status: String,
    @SerializedName("poster_path") @Expose val posterPath: String? = null,
    @SerializedName("backdrop_path") @Expose val backdropPath: String? = null,
    @SerializedName("vote_average") @Expose val rating: Float,
    @SerializedName("vote_count") @Expose val voteCount: Int,
    @SerializedName("release_date") @Expose val releaseDate: String? = null,
    @SerializedName("popularity") @Expose val popularity: Float? = null,
    @SerializedName("original_title") @Expose val originalTitle: String,
    @SerializedName("revenue") @Expose val revenue: Int,
    @SerializedName("runtime") @Expose val runtime: Int? = null
): Parcelable