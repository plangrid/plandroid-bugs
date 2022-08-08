package buggy.plandroid.com.plandroidbugs.room

import androidx.room.*
import io.reactivex.Flowable


@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun all(): Flowable<List<UserEntity>>

    @Query("SELECT * FROM users WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray?): List<UserEntity>

    @Query("SELECT * FROM users WHERE first_name LIKE :first AND last_name LIKE :last LIMIT 1")
    fun findByName(first: String?, last: String?): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(userEntityies: Collection<UserEntity>)

    @Update
    fun update(vararg userEntity: UserEntity)

    @Delete
    fun delete(userEntity: UserEntity)
}