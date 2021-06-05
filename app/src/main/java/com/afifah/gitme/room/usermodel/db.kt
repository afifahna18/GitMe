package com.afifah.gitme.room.usermodel

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [FavouriteUserData::class],
    version = 1
)

abstract class db: RoomDatabase() {
    companion object {
        var INSTANCE : db? = null

        fun getDatabase(context: Context): db?{
            if (INSTANCE == null){
               synchronized(db::class){
                   INSTANCE = Room.databaseBuilder(context.applicationContext, db::class.java, "User Database").build()
               }
            }
            return INSTANCE
        }
    }
    abstract fun favouriteUserDao(): FavouriteUserDao
}









