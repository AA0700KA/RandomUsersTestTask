package java.axon.test.sumsung.com.randomuserstesttask.presentation

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import io.reactivex.schedulers.Schedulers
import java.axon.test.sumsung.com.randomuserstesttask.network.UserApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.axon.test.sumsung.com.randomuserstesttask.R
import java.axon.test.sumsung.com.randomuserstesttask.adapter.UserAdapter
import java.axon.test.sumsung.com.randomuserstesttask.pojo.User


class MainActivity : AppCompatActivity(), UserAdapter.Listener {

    private val TAG = "user_data"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userAdapter = UserAdapter(this)

        setTitle(resources.getString(R.string.home))

        recycler_view.adapter = userAdapter
        recycler_view.layoutManager = LinearLayoutManager(this)

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                viewModel.loadUsers(20)
            }
        })

        viewModel.loadUsers(20)
        viewModel.allUsers.observe(this, Observer {
            val users  = it;
            userAdapter.update(users!!)
        })

        viewModel.error.observe(this, Observer {
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.grid) {
            recycler_view.layoutManager = GridLayoutManager(this, 2)
        } else {
            recycler_view.layoutManager = LinearLayoutManager(this)
        }
        return true
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


