package com.example.tiketcom.utilities

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * call back enqueue response from endpoint
 */
class CustomCallBack<T>: Callback<T> {
    var onSuccess: ((Response<T>) -> Unit)? = null
    var onFailure: ((t: Throwable?) -> Unit)? = null

    /**
     * Get response if it's success to call API to server
     */
    override fun onFailure(call: Call<T>, throwable: Throwable) {
        onFailure?.invoke(throwable)
    }

    /**
     * Get failure response if it's failed to call API to server
     */
    override fun onResponse(call: Call<T>, response: Response<T>) {
        onSuccess?.invoke(response)
    }
}
