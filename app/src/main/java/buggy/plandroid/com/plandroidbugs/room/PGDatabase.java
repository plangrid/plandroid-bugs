package buggy.plandroid.com.plandroidbugs.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(
        version = 2, // Last changed by Aggretsuko
        entities = {
                UserEntity.class,
        }
        )
public abstract class PGDatabase extends RoomDatabase {

    public abstract UserDao userDao();
}


