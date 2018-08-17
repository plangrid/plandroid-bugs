package buggy.plandroid.com.plandroidbugs;

import java.util.ArrayList;
import java.util.Collection;

import buggy.plandroid.com.plandroidbugs.room.UserEntity;

public class UserMapper {
    public static ArrayList<UserEntity> convert(Collection<UserWire> userWires) {
        ArrayList<UserEntity> userEntities = new ArrayList<>();
        for (UserWire userWire : userWires) {
            UserEntity entity = new UserEntity();
            entity.company = userWire.company;
            entity.email = userWire.email;
            entity.firstName = userWire.firstName;
            entity.lastName = userWire.lastName;
            entity.language = userWire.language;
            entity.userId = userWire.userId;

            userEntities.add(entity);
        }
        return userEntities;
    }
}
