package com.afifah.gitme.room.usermodel

import com.google.gson.annotations.SerializedName

data class UserData (
    @SerializedName("id")
    val id: Int,
    @SerializedName("login")
    val username: String,
    @SerializedName("avatar_url")
    val photos: String,
    @SerializedName("html_url")
    val url: String,
)