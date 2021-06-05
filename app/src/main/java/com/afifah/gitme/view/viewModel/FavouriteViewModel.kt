package com.afifah.gitme.view.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.afifah.gitme.room.usermodel.FavouriteUserDao
import com.afifah.gitme.room.usermodel.FavouriteUserData
import com.afifah.gitme.room.usermodel.db

class FavouriteViewModel(application: Application): AndroidViewModel(application) {

    private var userDao: FavouriteUserDao?
    private var userdb: db? = db.getDatabase(application)

    init {
        userDao = userdb?.favouriteUserDao()
    }

    fun getFavouriteUser(): LiveData<List<FavouriteUserData>>?{
        return userDao?.getFavouriteUser()
    }


}












