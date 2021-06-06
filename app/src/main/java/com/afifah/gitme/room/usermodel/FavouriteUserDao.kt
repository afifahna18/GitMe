package com.afifah.gitme.room.usermodel

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavouriteUserDao {
    @Insert
    suspend fun addToFavourite(favouriteUserDao: FavouriteUserData)

    @Query("SELECT * FROM user_favourite ")
    fun getFavouriteUser(): LiveData<List<FavouriteUserData>>

    @Query("SELECT count(*) FROM user_favourite WHERE user_favourite.id = :id ")
    suspend fun checkUser(id: Int): Int

    @Query("DELETE FROM user_favourite WHERE user_favourite.id = :id")
    suspend fun removeFromFavourite(id: Int): Int

    @Query("SELECT * FROM user_favourite ")
    fun findAll(): Cursor


}












