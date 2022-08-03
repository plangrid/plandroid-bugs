package buggy.plandroid.com.plandroidbugs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import buggy.plandroid.com.plandroidbugs.app.PlanDroidApp.Companion.getApp
import io.reactivex.android.schedulers.AndroidSchedulers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val userAdapter = UserAdapter()
        findViewById<RecyclerView>(R.id.recycler).apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        val userDao = getApp(applicationContext).database.userDao()

        // Listens to changes to the User database table.
        userDao.all().observeOn(AndroidSchedulers.mainThread())
            .subscribe { userAdapter.setUsers(it) }

    }
}