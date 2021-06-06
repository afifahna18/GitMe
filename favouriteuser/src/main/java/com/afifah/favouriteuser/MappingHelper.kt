package com.afifah.favouriteuser

import android.database.Cursor
import kotlin.collections.ArrayList

object MappingHelper {
    fun mapCursorToArrayList(cursor: Cursor?): ArrayList<UserData>{
        val list = ArrayList<UserData>()
        if (cursor != null){
            while (cursor.moveToNext()){
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.UserFavouriteColumns.ID))
                val username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.UserFavouriteColumns.USERNAME))
                val url = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.UserFavouriteColumns.URL))
                val photo = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.UserFavouriteColumns.PHOTO))
                list.add(
                    UserData(
                        id,
                        username,
                        photo,
                        url
                    )
                )
            }
        }
        return list
    }
}