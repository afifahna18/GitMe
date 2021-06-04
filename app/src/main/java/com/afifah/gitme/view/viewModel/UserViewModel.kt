package com.afifah.gitme.view.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afifah.gitme.room.api.RetrofitClient
import com.afifah.gitme.room.usermodel.UserData
import com.afifah.gitme.room.usermodel.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel: ViewModel() {

    val listGithubUser = MutableLiveData<ArrayList<UserData>>()

    fun setSearchUsers(query: String){
        RetrofitClient.apiInstance
            .getSearchUsers(query)
            .enqueue(object : Callback<UserResponse>{
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful){
                        listGithubUser.postValue(response.body()?.UserItem)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }

                }

            })
    }

    fun getSearchUsers(): LiveData<ArrayList<UserData>>{
        return listGithubUser
    }
}










