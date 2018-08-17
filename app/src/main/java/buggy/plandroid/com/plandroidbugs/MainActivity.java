package buggy.plandroid.com.plandroidbugs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import buggy.plandroid.com.plandroidbugs.app.PlanDroidApp;
import buggy.plandroid.com.plandroidbugs.room.UserDao;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = findViewById(R.id.recycler);
        UserAdapter userAdapter = new UserAdapter();
        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        UserDao userDao = PlanDroidApp.getApp(getApplicationContext()).getDatabase().userDao();

        userDao.getAll().observeOn(AndroidSchedulers.mainThread()).subscribe(it -> userAdapter.setUsers(it));
    }
}
