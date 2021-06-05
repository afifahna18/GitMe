package com.afifah.gitme.view.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afifah.gitme.room.api.RetrofitClient
import com.afifah.gitme.room.usermodel.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FollowersViewModel: ViewModel() {
    val listFollowers = MutableLiveData<ArrayList<UserData>>()

    fun setUserFollowers(username: String){
        RetrofitClient.apiInstance
            .getFollowersUsers(username)
            .enqueue(object : Callback<ArrayList<UserData>>{
                override fun onResponse(
                    call: Call<ArrayList<UserData>>,
                    response: Response<ArrayList<UserData>>
                ) {
                    if (response.isSuccessful){
                        listFollowers.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<UserData>>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })
    }

    fun getListFollowers(): LiveData<ArrayList<UserData>>{
        return  listFollowers
    }
}














