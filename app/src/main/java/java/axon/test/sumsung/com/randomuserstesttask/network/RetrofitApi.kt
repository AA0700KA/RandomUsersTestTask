package java.axon.test.sumsung.com.randomuserstesttask.network

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import java.axon.test.sumsung.com.randomuserstesttask.pojo.Result

interface RetrofitApi {

    companion object {
        const val BASE_URL = "https://randomuser.me/"
    }

    @GET("api")
    fun getUser() : Observable<Result>

}