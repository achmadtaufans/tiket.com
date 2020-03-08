package com.example.tiketcom.ui

import com.example.tiketcom.api.ApiClient
import com.example.tiketcom.api.ApiInterface
import com.example.tiketcom.utilities.CustomCallBack
import com.example.tiketcom.view_model.ItemList
import retrofit2.Call

/**
 * showing data github user list to UI
 */
class MainPresenter(initView: DefaultView.InitView) : DefaultView.GetUsers {
    private var initView: DefaultView.InitView = initView

    /**
     * get user list and showing to adapter
     */
    override fun getUserList(keyword: String?) {
        initView.showLoading()
        val apiInterface = ApiClient.getApiClient()!!.create(ApiInterface::class.java)
        val call = apiInterface.getUsers(keyword)

        /**
         * callback from endpoint response from callback retrofit function
         */
        call?.enqueue {
            onSuccess = { response ->
                initView.hideLoading()

                initView.userList(response.body()?.getItems() as MutableList<ItemList>?)

                val totalCount = response.body()?.getTotalCount()
                if (response.body()!!.getItems() == null || totalCount == 0) {
                    initView.userListFailure(
                        "No Result for '$keyword'",
                        "Try Searching for Other Users"
                    )
                }
            }
        }
    }
    
}

/**
 * Function to use custom callback retrofit
 */
fun<T> Call<T>.enqueue(callback: CustomCallBack<T>.() -> Unit) {
    val callBackKt = CustomCallBack<T>()

    callback.invoke(callBackKt)

    this.enqueue(callBackKt)
}
