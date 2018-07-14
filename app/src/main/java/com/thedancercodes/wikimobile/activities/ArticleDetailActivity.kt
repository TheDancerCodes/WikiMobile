package com.thedancercodes.wikimobile.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.thedancercodes.wikimobile.R
import kotlinx.android.synthetic.main.activity_article_detail.*


class ArticleDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        // This sets the supportActionBar using a v7 toolbar we added in our layout.
        setSupportActionBar(toolbar)

        // Set back button on the left side of the toolbar next to the title
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    // Handles back button press by finishing current activity & going back to previous one
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == android.R.id.home){
            finish()
        }
        return true
    }
}