package com.afifah.gitme.view.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afifah.gitme.room.api.RetrofitClient
import com.afifah.gitme.room.usermodel.ProfileUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileUserViewModel: ViewModel() {

    val user = MutableLiveData<ProfileUserResponse>()

    fun setUserProfile(username: String){
        RetrofitClient.apiInstance
            .getProfileUsers(username)
            .enqueue(object : Callback<ProfileUserResponse>{
                override fun onResponse(
                    call: Call<ProfileUserResponse>,
                    response: Response<ProfileUserResponse>
                ) {
                    if (response.isSuccessful){
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ProfileUserResponse>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })
    }
    fun getUserDetail(): LiveData<ProfileUserResponse>{
        return user
    }
}