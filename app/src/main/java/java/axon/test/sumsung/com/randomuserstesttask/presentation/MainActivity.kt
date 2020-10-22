package java.axon.test.sumsung.com.randomuserstesttask.presentation

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import io.reactivex.schedulers.Schedulers
import java.axon.test.sumsung.com.randomuserstesttask.network.UserApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.axon.test.sumsung.com.randomuserstesttask.R
import java.axon.test.sumsung.com.randomuserstesttask.adapter.UserAdapter
import java.axon.test.sumsung.com.randomuserstesttask.pojo.User


class MainActivity : AppCompatActivity(), UserAdapter.Listener {

    val TAG = "user_data"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userAdapter = UserAdapter(this)

        setTitle(resources.getString(R.string.home))

        recycler_view.adapter = userAdapter
        recycler_view.layoutManager = LinearLayoutManager(this)

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.loadUsers()
        viewModel.allUsers.observe(this, Observer {
            val users  = it;
            userAdapter.update(users!!)
        })

        viewModel.error.observe(this, Observer {
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
        })

    }

    override fun userInfo(user: User) {
        val intent = Intent(this, InfoUserActivity::class.java)
        intent.putExtra("date", user.dob.date)
        intent.putExtra("user_name", "${user.name.first} ${user.name.last}")
        intent.putExtra("phone", user.cell)
        intent.putExtra("email", user.email)
        intent.putExtra("picture", user.picture.large)
        intent.putExtra("gender", user.gender)
        startActivity(intent)
    }

}
