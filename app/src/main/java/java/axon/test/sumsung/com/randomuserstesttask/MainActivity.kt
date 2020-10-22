package java.axon.test.sumsung.com.randomuserstesttask

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.axon.test.sumsung.com.randomuserstesttask.network.RetrofitApi
import java.axon.test.sumsung.com.randomuserstesttask.pojo.Result

class MainActivity : AppCompatActivity() {

    val TAG = "user_data"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl(RetrofitApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitApi = retrofit.create(RetrofitApi::class.java)

        val call = retrofitApi.getUser()

        call.enqueue(object : Callback<Result> {

            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                val result = response.body()
                val user = result!!.results.get(0)

                Log.d(TAG, "onResponse: ${user.name.first} ${user.name.last} " +
                        "${user.cell} ${user.phone} ${user.email} ${user.picture.large}")
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

}
