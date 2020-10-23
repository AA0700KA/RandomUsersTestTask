package java.axon.test.sumsung.com.randomuserstesttask.presentation

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.axon.test.sumsung.com.randomuserstesttask.network.UserApiClient
import java.axon.test.sumsung.com.randomuserstesttask.pojo.User
import java.util.concurrent.locks.ReentrantLock

class MainViewModel : ViewModel() {

    val allUsers: MutableLiveData<List<User>> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()
    private var loaded : Boolean = false

    fun loadUsers(count : Int) {

        if (!loaded) {
            loaded = true
            val retrofitApi = UserApiClient.instance

            retrofitApi.getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        uploadUser(result!!.results.get(0), count)
                    },
                    { e -> sendError(e.message) }
                )
        }

    }


    private fun sendError(errorMassage : String?) {
        error.value = errorMassage
    }

    private fun uploadUser(user : User, count : Int) {
        val allUsersList = mutableListOf<User>()

        for (x in 0 until count) {
            allUsersList.add(user)
        }

        allUsers.value = allUsersList
        loaded = false
    }

}