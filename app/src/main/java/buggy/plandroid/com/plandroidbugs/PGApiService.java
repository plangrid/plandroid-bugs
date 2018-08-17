package buggy.plandroid.com.plandroidbugs;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface PGApiService {

    @GET("projects/fc6facf8-c69c-4780-bac1-1774bd91cc8c/users")
    Observable<UserList> getAllUsers();


    @GET("projects/fc6facf8-c69c-4780-bac1-1774bd91cc8c/users/540e0bdcde37b40013533497")
    Call<UserWire> getUser();

    @PATCH("projects/fc6facf8-c69c-4780-bac1-1774bd91cc8c/users/{userId}")
    Observable<ResponseBody> changeUserRole(@Path("userId") String userId, @Body Map<String, String> parameters);
}
