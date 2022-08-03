package buggy.plandroid.com.plandroidbugs

import buggy.plandroid.com.plandroidbugs.room.UserEntity

object UserMapper {
    fun convert(userWires: List<UserWire>): List<UserEntity> {
        return userWires.map {
            UserEntity(
                userId = it.userId,
                email = it.email,
                firstName = it.firstName,
                lastName = it.lastName,
                company = it.company,
                title = it.title,
                language = it.language,
                roleUid = it.role?.uid ?: UserAdapter.ADMIN_ROLE
            )
        }
    }
}