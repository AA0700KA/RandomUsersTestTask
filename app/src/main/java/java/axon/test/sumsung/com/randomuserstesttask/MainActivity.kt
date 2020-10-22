package java.axon.test.sumsung.com.randomuserstesttask

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.axon.test.sumsung.com.randomuserstesttask.network.RetrofitApi
import java.axon.test.sumsung.com.randomuserstesttask.network.UserApiClient
import java.axon.test.sumsung.com.randomuserstesttask.pojo.Result
import io.reactivex.android.schedulers.AndroidSchedulers


class MainActivity : AppCompatActivity() {

    val TAG = "user_data"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         val userApiClient = UserApiClient()

         val retrofitApi = userApiClient.getRetrofitApi()

        retrofitApi.getUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {result ->
                    val user = result!!.results.get(0)
                    Log.d(TAG, "onResponse: ${user.name.first} ${user.name.last} " +
                            "${user.cell} ${user.phone} ${user.email} ${user.picture.large}")
                 },
                { e -> Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show() }
            )


    }

}
