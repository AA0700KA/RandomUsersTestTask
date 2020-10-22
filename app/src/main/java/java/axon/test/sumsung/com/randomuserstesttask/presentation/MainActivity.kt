package java.axon.test.sumsung.com.randomuserstesttask.presentation

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import io.reactivex.schedulers.Schedulers
import java.axon.test.sumsung.com.randomuserstesttask.network.UserApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import java.axon.test.sumsung.com.randomuserstesttask.R


class MainActivity : AppCompatActivity() {

    val TAG = "user_data"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.loadUsers()
        viewModel.allUsers.observe(this, Observer {
            val users  = it;

            for (user in users!!) {
                Log.d(TAG, "onResponse: ${user.name.first} ${user.name.last} " +
                        "${user.cell} ${user.phone} ${user.email} ${user.picture.large}")
            }

        })

        viewModel.error.observe(this, Observer {
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
        })


    }

}
