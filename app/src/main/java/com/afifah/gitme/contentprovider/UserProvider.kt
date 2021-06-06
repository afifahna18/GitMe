package com.afifah.gitme.contentprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.afifah.gitme.room.usermodel.FavouriteUserDao
import com.afifah.gitme.room.usermodel.db

class UserProvider : ContentProvider() {

    companion object{
        const val AUTHORITY = "com.afifah.gitme"
        const val TABLE_NAME = "user_favourite"
        const val ID_DATA_USER_FAVOURITE = 1
        val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI(AUTHORITY, TABLE_NAME, ID_DATA_USER_FAVOURITE)
        }
    }

    private lateinit var userDao: FavouriteUserDao

    override fun onCreate(): Boolean{
        userDao = context?.let { ctx ->
            db.getDatabase(ctx)?.favouriteUserDao()
        }!!
        return false
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        var cursor: Cursor?
        when(uriMatcher.match(uri)){
            ID_DATA_USER_FAVOURITE -> {
                cursor = userDao.findAll()
                if (context != null){
                    cursor.setNotificationUri(context?.contentResolver, uri)
                }
            }
            else -> {
                cursor = null
            }
        }
        return  cursor
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return 0
    }
}







