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
                .build()
    }

    override fun onCreate() {
        super.onCreate()

        syncUsersFromServer()
    }

    private fun syncUsersFromServer() {
        PGApi().projectUsersObservable.observeOn(Schedulers.io()).subscribe(
                { userList -> database.userDao().insertAll(
                        convert(userList.userWires
                                .filter { it.email != "ghalib+1237123612632@plangrid.com" && it.email != "amy.tang@plangrid.com" })) },
                // This filter is to prevent interviewees from clicking on themselves and me.
                { e -> Log.d("MainActivity", e.message ?: "") })
    }
}

