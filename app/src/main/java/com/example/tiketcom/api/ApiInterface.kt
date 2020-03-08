package com.example.tiketcom.api

import com.example.tiketcom.view_model.GitUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * set API to get user from endpoint
 */
interface ApiInterface {
    /**
     * get user list
     */
    @GET("search/users")
    fun getUsers(@Query("q") keyword: String?): Call<GitUser?>?
}
