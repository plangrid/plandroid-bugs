package buggy.plandroid.com.plandroidbugs.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [
        UserEntity::class
    ]
)
abstract class PGDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}