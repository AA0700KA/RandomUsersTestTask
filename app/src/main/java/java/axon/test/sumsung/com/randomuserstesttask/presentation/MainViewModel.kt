package java.axon.test.sumsung.com.randomuserstesttask.presentation

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.axon.test.sumsung.com.randomuserstesttask.network.UserApiClient
import java.axon.test.sumsung.com.randomuserstesttask.pojo.User

class MainViewModel : ViewModel() {

    val allUsers : MutableLiveData<List<User>> = MutableLiveData()
    val error : MutableLiveData<String> = MutableLiveData()

    fun loadUsers() {
        val userApiClient = UserApiClient()
        val retrofitApi = userApiClient.getRetrofitApi()

        retrofitApi.getUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {result ->
                    uploadUser(result!!.results.get(0))
                },
                { e -> sendError(e.message) }
            )
    }

    private fun sendError(errorMassage : String?) {
        error.value = errorMassage
    }

    private fun uploadUser(user : User) {
        val allUsersList = mutableListOf<User>()

        for (x in 0 until 40) {
            allUsersList.add(user)
        }

        allUsers.value = allUsersList
    }

}