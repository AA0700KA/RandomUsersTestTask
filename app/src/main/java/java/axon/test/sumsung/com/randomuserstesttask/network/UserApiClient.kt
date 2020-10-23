package java.axon.test.sumsung.com.randomuserstesttask.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object UserApiClient {

    private var ourInstance : RetrofitApi? = null

    val instance : RetrofitApi
     get() {
         if (ourInstance == null) {
             val interceptor = HttpLoggingInterceptor()
             interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
             val client = OkHttpClient.Builder().addInterceptor(interceptor).build()


             val gson = GsonBuilder()
                 .create()

             val retrofit = Retrofit.Builder()
                 .baseUrl(RetrofitApi.BASE_URL)
                 .addConverterFactory(GsonConverterFactory.create(gson))
                 .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                 .client(client)
                 .build()

             ourInstance = retrofit.create(RetrofitApi::class.java)
         }

         return ourInstance!!
     }

}