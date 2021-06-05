package com.afifah.gitme.room.usermodel

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "user_favourite")

data class FavouriteUserData(
    @PrimaryKey
    @SerializedName("login")
    val username: String,
    @SerializedName("id")
    val id: Int,


) : Serializable












