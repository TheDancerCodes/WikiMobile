package com.thedancercodes.wikimobile.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.gson.Gson
import com.thedancercodes.wikimobile.R
import com.thedancercodes.wikimobile.WikiApplication
import com.thedancercodes.wikimobile.managers.WikiManager
import com.thedancercodes.wikimobile.models.WikiPage
import kotlinx.android.synthetic.main.activity_article_detail.*
import org.jetbrains.anko.toast


class ArticleDetailActivity : AppCompatActivity() {

    // Variables
    private var wikiManager: WikiManager? = null
    private var currentPage: WikiPage? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        // Instantiate WikiManager
        wikiManager = (applicationContext as WikiApplication).wikiManager

        // This sets the supportActionBar using a v7 toolbar we added in our layout.
        setSupportActionBar(toolbar)

        // Set back button on the left side of the toolbar next to the title
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        // Get the Page from the Extras
        val wikiPageJson = intent.getStringExtra("page")

        // Use Gson that was pulled in with Fuel to deserialize this String into a proper
        // WikiPage Model
        currentPage = Gson().fromJson<WikiPage>(wikiPageJson, WikiPage::class.java)

        // Update Toolbar Title with Page Title from the WikiPage
        supportActionBar?.title = currentPage?.title

        // Useful handler to make sure we properly load the URL rather than calling over to the WebView
        article_detail_webview?.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                // return super.shouldOverrideUrlLoading(view, request)
                return true
            }
        }

        // Take auto-mapped article_detail_webview load the full url of the WikiPage passed in.
        article_detail_webview.loadUrl(currentPage!!.fullurl)

        // Add the current page to the History cache and local repository
        wikiManager?.addHistory(currentPage!!)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.article_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        // Handles back button press by finishing current activity & going back to previous one
        if (item!!.itemId == android.R.id.home) {
            finish()
        } else if (item!!.itemId == R.id.action_favorite) {

            try {

                // Determine whether article is already a favorite or not
                if (wikiManager!!.getIsFavorite(currentPage!!.pageid!!)){
                    wikiManager!!.removeFavorite(currentPage!!.pageid!!)
                    toast("Article removed from favorites")
                }
                else{
                    wikiManager!!.addFavorite(currentPage!!)
                    toast("Article added to favorites")
                }

            } catch (ex: Exception){
                toast("Unable to update this article.")
            }
        }
        return true
    }
}