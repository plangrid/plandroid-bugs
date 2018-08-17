package buggy.plandroid.com.plandroidbugs;

import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.kotlin.KotlinModule;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class PGApi {

    public static final String BASE_URL = "https://plangrid-c-api-dispatcher-test.planfront.net";
    private static final String AUTH_KEY = "521dd37f15b497e01fd7aeacab0892ec";

    private final ObjectMapper objectMapper = getObjectMapper();
    private final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(provideUrlAndHeaderInterceptor())
            .build();

    private Interceptor provideUrlAndHeaderInterceptor() {
        return chain -> chain.proceed(chain.request().newBuilder()
                                           .addHeader("Accept", "application/vnd.plangrid+json; version=1")
                                           .addHeader("Authorization", basicAuth(AUTH_KEY + ":\"\""))
                                           .build());
    }

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(JacksonConverterFactory.create(new ObjectMapper()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
    private final PGApiService apiService = retrofit.create(PGApiService.class);


    public Observable<UserList> getProjectUsersObservable() {
        return apiService.getAllUsers().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public void changeUserRole(String userUid, String roleUid) {
        HashMap<String, String> map = new HashMap<>();
        map.put("role_uid", roleUid);
        try {
            apiService.changeUserRole(userUid, map).subscribeOn(Schedulers.computation())
                      .doOnError(
                              e -> Log.d("PGApi", "Failed to change roles", e))
                      .subscribe();
        } catch (Exception e) {
            Log.e("PGApi", e.getMessage(), e);
        }
    }

    public static String basicAuth(String toEncode) {
        return String.format("Basic %s", new String(Base64.encode(toEncode.getBytes(), Base64.DEFAULT))).trim();
    }

    public static ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY, false);
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.registerModule(new KotlinModule());
        return mapper;
    }
}
