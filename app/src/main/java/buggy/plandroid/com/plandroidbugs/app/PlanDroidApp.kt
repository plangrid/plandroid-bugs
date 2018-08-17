package buggy.plandroid.com.plandroidbugs.app

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import android.util.Log
import buggy.plandroid.com.plandroidbugs.PGApi
import buggy.plandroid.com.plandroidbugs.UserMapper.convert
import buggy.plandroid.com.plandroidbugs.room.PGDatabase
import io.reactivex.schedulers.Schedulers

class PlanDroidApp : Application() {

    companion object {
        @JvmStatic
        fun getApp(context: Context): PlanDroidApp {
            return context.applicationContext as PlanDroidApp
        }
    }

    val database: PGDatabase by lazy {
        Room.databaseBuilder(this,
                PGDatabase::class.java, "database-name")
                .addMigrations(Migration1To2())
                .build()
    }

    override fun onCreate() {
        super.onCreate()

        PGApi().projectUsersObservable.observeOn(Schedulers.io()).subscribe(
                { blah -> database.userDao().insertAll(convert(blah.userWires)) },
                { e -> Log.d("MainActivity", e.message) })
    }
}

