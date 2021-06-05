package com.afifah.gitme.view.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.afifah.gitme.room.api.RetrofitClient
import com.afifah.gitme.room.usermodel.FavouriteUserDao
import com.afifah.gitme.room.usermodel.FavouriteUserData
import com.afifah.gitme.room.usermodel.ProfileUserResponse
import com.afifah.gitme.room.usermodel.db
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileUserViewModel(application: Application) : AndroidViewModel(application) {

    val user = MutableLiveData<ProfileUserResponse>()

    private var userDao: FavouriteUserDao?
    private var userdb: db? = db.getDatabase(application)

    init {
        userDao = userdb?.favouriteUserDao()
    }

    fun setUserProfile(username: String) {
        RetrofitClient.apiInstance
            .getProfileUsers(username)
            .enqueue(object : Callback<ProfileUserResponse> {
                override fun onResponse(
                    call: Call<ProfileUserResponse>,
                    response: Response<ProfileUserResponse>
                ) {
                    if (response.isSuccessful) {
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ProfileUserResponse>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })
    }

    fun getUserDetail(): LiveData<ProfileUserResponse> {
        return user
    }

    fun addToFavourite(login: String, id:Int, avatarUrl: String, url: String){
        CoroutineScope(Dispatchers.IO).launch {
            var user = FavouriteUserData(
                login,
                id,
                avatarUrl,
                url
            )
            userDao?.addToFavourite(user)
        }
    }

    suspend fun checkUser(id: Int) = userDao?.checkUser(id)

    fun removeFromFavourite(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            userDao?.removeFromFavourite(id)
        }
    }


}















