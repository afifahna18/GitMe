package com.afifah.gitme.room.api

import com.afifah.gitme.room.usermodel.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponse>
}