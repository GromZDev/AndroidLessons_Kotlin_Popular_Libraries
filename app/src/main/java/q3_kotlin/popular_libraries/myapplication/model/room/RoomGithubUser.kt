package q3_kotlin.popular_libraries.myapplication.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

/** id должен быть вместе с логином, поэтому null не ставлю*/
@Entity
class RoomGithubUser(
    @PrimaryKey val id: String,
    var login: String,
    var avatarUrl: String? = null,
    var reposUrl: String? = null
)