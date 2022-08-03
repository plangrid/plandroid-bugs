package buggy.plandroid.com.plandroidbugs.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "uid")
    val userId: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "first_name")
    val firstName: String,

    @ColumnInfo(name = "last_name")
    val lastName: String,

    @ColumnInfo(name = "company")
    val company: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "language")
    val language: String,

    @ColumnInfo(name = "role_uid")
    val roleUid: String,
)
