package com.afifah.favouriteuser

import android.net.Uri
import android.provider.BaseColumns
object DatabaseContract {

    const val AUTHORITY = "com.afifah.gitme"
    const val SCHEME = "content"

    internal class UserFavouriteColumns: BaseColumns{
        companion object{
            const val TABLE_NAME = "username"
            const val ID = "id"
            const val USERNAME = "login"
            const val PHOTO = "photo"
            const val URL = "url"

            val CONTENT_URL = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()
        }
    }
}