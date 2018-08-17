package buggy.plandroid.com.plandroidbugs.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

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

    @Insert
    void insertAll(Collection<UserEntity> userEntitys);

    @Delete
    void delete(UserEntity userEntity);
}
