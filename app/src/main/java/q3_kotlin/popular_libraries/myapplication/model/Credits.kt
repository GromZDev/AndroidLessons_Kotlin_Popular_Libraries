package q3_kotlin.popular_libraries.myapplication.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Credits(
    @SerializedName("id") @Expose val id: Long,
    @SerializedName("cast") @Expose val cast: List<Cast>
) : Parcelable

@Parcelize
data class Cast(
    @Expose val id: Long,
    @Expose val cast_id: Long,
    @Expose val credit_id: String,
    @Expose val character: String,
    @Expose val gender: Int?,
    @Expose val name: String,
    @Expose val profile_path: String?
) : Parcelable