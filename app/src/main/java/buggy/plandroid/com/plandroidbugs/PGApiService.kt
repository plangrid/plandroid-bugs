package buggy.plandroid.com.plandroidbugs

import retrofit2.http.GET
import buggy.plandroid.com.plandroidbugs.UserList
import buggy.plandroid.com.plandroidbugs.UserWire
import io.reactivex.Observable
import retrofit2.http.PATCH
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Path

interface PGApiService {
    @GET("projects/fc6facf8-c69c-4780-bac1-1774bd91cc8c/users")
    fun getAllUsers(): Observable<UserList>

    @GET("projects/fc6facf8-c69c-4780-bac1-1774bd91cc8c/users/540e0bdcde37b40013533497")
    fun getUser(): Call<UserWire>

    @PATCH("projects/fc6facf8-c69c-4780-bac1-1774bd91cc8c/users/{userId}")
    fun changeUserRole(
        @Path("userId") userId: String,
        @Body parameters: Map<String, String>
    ): Observable<ResponseBody?>
}