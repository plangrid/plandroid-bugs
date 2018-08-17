package buggy.plandroid.com.plandroidbugs.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@Entity(tableName = "users")
public class UserEntity {

    @NonNull @PrimaryKey @ColumnInfo(name = "uid") public String userId;
    @ColumnInfo(name = "email") public String email;
    @ColumnInfo(name = "first_name") public String firstName;
    @ColumnInfo(name = "last_name") public String lastName;
    @ColumnInfo(name = "company") public String company;
    @ColumnInfo(name = "title") public String title;
    @ColumnInfo(name = "language") public String language;
    @ColumnInfo(name = "role_uid") public String roleUid;
}
