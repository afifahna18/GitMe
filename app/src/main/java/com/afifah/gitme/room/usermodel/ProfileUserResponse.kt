package com.afifah.gitme.room.usermodel

import com.google.gson.annotations.SerializedName

data class ProfileUserResponse (
    @SerializedName("id")
    val id: Int,
    @SerializedName("avatar_url")
    val photo: String,
    @SerializedName("login")
    val username: String,
    @SerializedName("name")
    val fullName: String,
    @SerializedName("company")
    val company: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("bio")
    val bio: String,
    @SerializedName("following")
    val following: Int,
    @SerializedName("followers")
    val followers: Int,
    @SerializedName("public_repos")
    val repository: Int,
    @SerializedName("followers_url")
    val urlFollowers: String,
    @SerializedName("following_url")
    val urlFollowing: String,
)










