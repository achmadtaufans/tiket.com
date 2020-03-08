package com.example.tiketcom.view_model

import com.google.gson.annotations.SerializedName

/**
 * get name, photo github users from endpoint
 */
class ItemList {
    @SerializedName("login")
    private var name: String? = null
    @SerializedName("avatar_url")
    private var avatar: String? = null

    /**
     * get name github user
     */
    fun getName(): String? {
        return name
    }

    /**
     * get photo github user
     */
    fun getAvatar(): String? {
        return avatar
    }
}
