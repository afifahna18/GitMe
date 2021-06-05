package com.afifah.gitme.room.api

import com.afifah.gitme.room.usermodel.ProfileUserResponse
import com.afifah.gitme.room.usermodel.UserData
import com.afifah.gitme.room.usermodel.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getProfileUsers(
        @Path("username") username: String
    ): Call<ProfileUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowersUsers(
        @Path("username") username: String
    ): Call<ArrayList<UserData>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowingUsers(
        @Path("username") username: String
    ): Call<ArrayList<UserData>>


}









