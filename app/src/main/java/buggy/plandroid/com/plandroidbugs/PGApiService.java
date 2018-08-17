package buggy.plandroid.com.plandroidbugs;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PGApiService {

    @GET("projects/fc6facf8-c69c-4780-bac1-1774bd91cc8c/users")
    Observable<UserList> getAllUsers();


    @GET("projects/fc6facf8-c69c-4780-bac1-1774bd91cc8c/users/540e0bdcde37b40013533497")
    Call<UserWire> getUser();
}
