package q3_kotlin.popular_libraries.myapplication.model.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGithubUser::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
class RoomGithubRepository(
    @PrimaryKey val id: String,
    val name: String,
    val forksCount: String,
    val userId: String
)