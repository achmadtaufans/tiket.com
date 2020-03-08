package com.example.tiketcom.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * set base URL for api client
 */
object ApiClient {
    private const val BASE_URL = "https://api.github.com/"
    private var retrofit: Retrofit? = null

    /**
     * get api endpoint to hit endpoint
     */
    fun getApiClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}