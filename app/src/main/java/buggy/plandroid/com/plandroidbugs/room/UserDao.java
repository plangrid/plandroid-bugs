package buggy.plandroid.com.plandroidbugs.room;

import android.arch.persistence.room.*;

import java.util.Collection;
import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    Flowable<List<UserEntity>> getAll();

    @Query("SELECT * FROM users WHERE uid IN (:userIds)")
    List<UserEntity> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM users WHERE first_name LIKE :first AND "
           + "last_name LIKE :last LIMIT 1")
    UserEntity findByName(String first, String last);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Collection<UserEntity> userEntitys);

    @Update
    void update(UserEntity... userEntity);

    @Delete
    void delete(UserEntity userEntity);
}
