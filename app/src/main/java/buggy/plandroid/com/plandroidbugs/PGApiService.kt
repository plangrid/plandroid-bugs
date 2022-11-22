package buggy.plandroid.com.plandroidbugs

import retrofit2.http.GET
import io.reactivex.Observable
import retrofit2.Call

interface PGApiService {
    @GET("projects/fc6facf8-c69c-4780-bac1-1774bd91cc8c/users")
    fun getAllUsers(): Observable<UserList>

    @GET("projects/fc6facf8-c69c-4780-bac1-1774bd91cc8c/users/540e0bdcde37b40013533497")
    fun getUser(): Call<UserWire>
}