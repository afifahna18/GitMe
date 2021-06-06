package com.afifah.favouriteuser

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FavouriteViewModel(application: Application): AndroidViewModel(application) {

    private var list = MutableLiveData<ArrayList<UserData>>()


    fun setFavourite(context: Context){
        val cursor = context.contentResolver.query(
            DatabaseContract.UserFavouriteColumns.CONTENT_URL,
            null,
            null,
            null,
            null
        )

        Log.d("FavouriteViewModel", cursor.toString())

        val listConverted = MappingHelper.mapCursorToArrayList(cursor)
        list.postValue(listConverted)
    }

    fun getFavouriteUser(): LiveData<ArrayList<UserData>>?{
        return list
    }
}












