package com.example.tiketcom.view_model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * get github user list and set into general class
 */
class GitUser {
    @SerializedName("items")
    @Expose
    private var items: List<ItemList?>? = null

    @SerializedName("total_count")
    @Expose
    private var totalCount = 0

    /**
     * function for every class to get github user after hit endpoint
     */
    fun getItems(): List<ItemList?>? {
        return items
    }

    /**
     * function for every class to get how many github user found
     */
    fun getTotalCount(): Int {
        return totalCount
    }
}