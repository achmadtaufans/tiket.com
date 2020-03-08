package com.example.tiketcom

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.MenuItemCompat
import androidx.core.view.MenuItemCompat.OnActionExpandListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiketcom.ui.Adapter
import com.example.tiketcom.ui.MainPresenter
import com.example.tiketcom.ui.DefaultView
import com.example.tiketcom.view_model.ItemList

/**
 * activity for getting username from github
 */
class MainActivity : AppCompatActivity(), DefaultView.InitView {
    private lateinit var recyclerView: RecyclerView
    private var progressBar: ProgressBar? = null
    private var mainPresenter: MainPresenter? = null
    private var emptyView: RelativeLayout? = null
    private var errorTitle: TextView? = null
    private var errorMessage: TextView? = null
    private var adapter: Adapter? = null
    private var users: MutableList<ItemList>? = null

    /**
     * set contentview and setup all element
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        init()
        
        mainPresenter = MainPresenter(this)
    }

    /**
     * initialize element
     */
    private fun init() {
        emptyView = findViewById(R.id.empty_view)
        errorTitle = findViewById(R.id.errorTitle)
        errorMessage = findViewById(R.id.errorMessage)
        progressBar = findViewById(R.id.progress)
        recyclerView = findViewById(R.id.recycler)
        
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
    }

    /**
     * searchable toolbar component
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        
        inflater.inflate(R.menu.menu, menu)
        
        val searchItem = menu.findItem(R.id.action_search)
        val searchManager =
            getSystemService(Context.SEARCH_SERVICE) as SearchManager

        searchItem.actionView.setBackgroundColor(ContextCompat.getColor(baseContext, R.color.design_default_color_primary_dark))

        //Setup action on toolbar action
        var searchView = menu.findItem(R.id.action_search).actionView as SearchView
        
        searchView.setBackgroundColor(ContextCompat.getColor(baseContext, R.color.design_default_color_primary_dark))
        searchView.queryHint = "Search"
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        //expand and collapse toolbar to show search element
        MenuItemCompat.setOnActionExpandListener(searchItem, object : OnActionExpandListener {
            //show search element for user input
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                return true
            }

            //remove list and hide search element and show default view
            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                if (users != null) {
                    users!!.clear()
                }

                defaultView(View.VISIBLE, "Tiket.com", "Search Github Users")
                return true
            }
        })

        searchView.setIconifiedByDefault(false)
        searchView.setOnQueryTextListener(object : OnQueryTextListener {
            //hit endpoint whenever user pressed search icon on keyboard
            override fun onQueryTextSubmit(query: String?): Boolean {
                mainPresenter!!.getUserList(query)
                return false
            }

            //hit endpoint whenever user type any words
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != "") {
                    mainPresenter!!.getUserList(newText)
                } else {
                    if (users != null) {
                        users!!.clear()
                    }

                    defaultView(View.VISIBLE, "Tiket.com", "Search Github Users")
                }
                return false
            }
        })
        return true
    }

    /**
     * show loading progress and hide default view
     */
    override fun showLoading() {
        progressBar!!.visibility = View.VISIBLE
        defaultView(View.INVISIBLE, "", "")
    }

    /**
     * hide loading when loading progress visible
     */
    override fun hideLoading() {
        progressBar!!.visibility = View.INVISIBLE
    }

    /**
     * get user list from endpoint and set to adapter
     */
    override fun userList(items: MutableList<ItemList>?) {
        recyclerView!!.visibility = View.VISIBLE
        if (users != null) {
            users!!.clear()
        }
        users = items!!
        adapter = Adapter(users!!, this)
        recyclerView.adapter = adapter
        adapter!!.notifyDataSetChanged()
    }

    /**
     * show error message whenever user is not exists
     */
    override fun userListFailure(errorMessage: String?, keyword: String?) {
        defaultView(View.VISIBLE, errorMessage!!, keyword!!)
    }

    /**
     * set default view when user not in searching github users
     */
    private fun defaultView(
        visibility: Int,
        title: String,
        message: String
    ) {
        recyclerView!!.visibility = View.GONE
        emptyView!!.visibility = visibility
        errorTitle!!.text = title
        errorMessage!!.text = message
    }
}
