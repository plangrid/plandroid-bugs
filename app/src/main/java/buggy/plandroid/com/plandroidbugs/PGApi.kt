package buggy.plandroid.com.plandroidbugs

import android.util.Base64
import android.util.Log
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

class PGApi {
    private val objectMapper = getObjectMapper()
    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(provideUrlAndHeaderInterceptor())
        .build()

    private fun provideUrlAndHeaderInterceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            chain.proceed(
                chain.request().newBuilder()
                    .addHeader("Accept", "application/vnd.plangrid+json; version=1")
                    .addHeader("Authorization", basicAuth(AUTH_KEY + ":\"\""))
                    .build()
            )
        }
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(JacksonConverterFactory.create(ObjectMapper()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    private val apiService = retrofit.create(PGApiService::class.java)

    val projectUsersObservable: Observable<UserList> =
        apiService.getAllUsers().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun changeUserRole(userUid: String, roleUid: String) {
        val map = mapOf("role_uid" to roleUid)
        try {
            apiService.changeUserRole(userUid, map).subscribeOn(Schedulers.computation())
                .doOnError { e: Throwable? -> Log.d("PGApi", "Failed to change roles", e) }
                .subscribe()
        } catch (e: Exception) {
            Log.e("PGApi", e.message, e)
        }
    }

    companion object {
        const val BASE_URL = "https://plangrid-c-api-dispatcher-test.planfront.net"
        private const val AUTH_KEY = "521dd37f15b497e01fd7aeacab0892ec"

        fun basicAuth(toEncode: String): String {
            return String.format(
                "Basic %s",
                String(Base64.encode(toEncode.toByteArray(), Base64.DEFAULT))
            ).trim { it <= ' ' }
        }

        fun getObjectMapper(): ObjectMapper {
            val mapper = ObjectMapper()
            mapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false)
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            mapper.configure(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY, false)
            mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
            mapper.registerModule(KotlinModule())
            return mapper
        }
    }
}