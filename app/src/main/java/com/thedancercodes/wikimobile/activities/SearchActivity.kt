package com.thedancercodes.wikimobile.activities

import android.app.SearchManager
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import com.thedancercodes.wikimobile.R
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        // Set the supportActionBar using a v7 toolbar we added in our layout.
        setSupportActionBar(toolbar)

        // Set back button on the left side of the toolbar next to the title
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home){
            finish()
        }
        return true
    }


    // Set up to get Search Functionality working.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        // Inflate menu from menu resource that we have
        menuInflater.inflate(R.menu.search_menu, menu)

        // Get specific item created by its ID
        val searchItem = menu!!.findItem(R.id.action_search)

        // Get reference to SearchManager Service
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        // Get reference to our SearchView from the actionView we assigned in our menu resource &
        // set SearchableInfo to the current component
        val searchView = searchItem!!.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        // It is focused and expanded immediately we start the Activity
        searchView.setIconifiedByDefault(false)
        searchView.requestFocus()

        // OnQueryTextListener object: Helps us handle submissions or when text is changed
        // in the Search bar
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                // Do the search and update the elements

                println("updated search")

                return false
            }

            override fun onQueryTextChange(s: String?): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}
