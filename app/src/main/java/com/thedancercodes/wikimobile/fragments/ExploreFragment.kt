package com.thedancercodes.wikimobile.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AlertDialog
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thedancercodes.wikimobile.R
import com.thedancercodes.wikimobile.activities.SearchActivity
import com.thedancercodes.wikimobile.adapters.ArticleCardRecyclerAdapter
import com.thedancercodes.wikimobile.providers.ArticleDataProvider

/**
 * A simple [Fragment] subclass.
 *
 */
class ExploreFragment : Fragment() {

    // Private variables for our Views.
    private val articleProvider: ArticleDataProvider = ArticleDataProvider()
    var searchCardView: CardView? = null
    var exploreRecycler: RecyclerView? = null
    var refresher: SwipeRefreshLayout? = null
    var adapter: ArticleCardRecyclerAdapter = ArticleCardRecyclerAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater!!.inflate(R.layout.fragment_explore, container, false)

        // Reference to the SearchCardView, RecyclerView & SwipeToRefresh
        searchCardView = view.findViewById<CardView>(R.id.search_card_view)
        exploreRecycler = view.findViewById<RecyclerView>(R.id.explore_article_recycler)
        refresher = view.findViewById<SwipeRefreshLayout>(R.id.refresher)

        searchCardView!!.setOnClickListener {

            // Set an Intent that goes to the SearchActivity
            val searchIntent = Intent(context, SearchActivity::class.java)
            context!!.startActivity(searchIntent)
        }

        // Wire up our adapter with our RecyclerView
        exploreRecycler!!.layoutManager = LinearLayoutManager(context)
        exploreRecycler!!.adapter = adapter

        // Listener for when pull-to-refresh is invoked.
        refresher?.setOnRefreshListener {
            getRandomArticles()
        }

        // Ensure we call to get random Articles when the view id first created so that they
        // show immediately the app starts or when the ExploreFragment comes into view & is created
        // for the first time.
        getRandomArticles()

        return view
    }

    // This will we useful when we add pull-to-refresh later.
    private fun getRandomArticles() {
        refresher?.isRefreshing = true

        try {
            articleProvider.getRandom(15, { wikiResult ->

                // Update results when we get articles
                adapter.currentResults.clear()
                adapter.currentResults.addAll(wikiResult.query!!.pages)

                // When we update the data-set in our adapter, we call it on the UI thread.
                activity?.runOnUiThread {
                    adapter.notifyDataSetChanged()
                    refresher?.isRefreshing = false
                }

            })
        }
        catch (ex :Exception){

            // Show Alert
            val builder = activity?.let { AlertDialog.Builder(it) }
            builder?.setMessage(ex.message)?.setTitle("oops!")


            val dialog = builder?.create()
            dialog?.show()
        }
    }


    // Wire up clicking the Card View & having it navigate to the SearchActivity

} // Required empty public constructor
