package q3_kotlin.popular_libraries.myapplication.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
class GithubSpecificUser(
    @Expose val id: String,
    @Expose val name: String,
    @Expose val forksCount: String
) : Parcelable
