package buggy.plandroid.com.plandroidbugs.app

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


class Migration1To2 : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE users ADD role_uid TEXT")
    }

}