package com.example.tiketcom.ui

import com.example.tiketcom.view_model.ItemList

/**
 * set default view component
 */
interface DefaultView {
    /**
     * initialize view
     */
    interface InitView {
        fun showLoading()
        fun hideLoading()
        fun userList(users: MutableList<ItemList>?)
        fun userListFailure(errorMessage: String?, keyword: String?)
    }

    /**
     * get user from list endpoint
     */
    interface GetUsers {
        fun getUserList(keyword: String?)
    }
}