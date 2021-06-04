package com.afifah.gitme.room.usermodel

import com.google.gson.annotations.SerializedName

data class UserResponse (
    @SerializedName("items")
    val UserItem: ArrayList<UserData>)