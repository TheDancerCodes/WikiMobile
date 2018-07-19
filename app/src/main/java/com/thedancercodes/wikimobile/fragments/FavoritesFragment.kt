package com.thedancercodes.wikimobile.fragments


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.thedancercodes.wikimobile.R
import com.thedancercodes.wikimobile.WikiApplication
import com.thedancercodes.wikimobile.adapters.ArticleCardRecyclerAdapter
import com.thedancercodes.wikimobile.adapters.ArticleListItemRecyclerAdapter
import com.thedancercodes.wikimobile.managers.WikiManager
import com.thedancercodes.wikimobile.models.WikiPage
import kotlinx.android.synthetic.main.fragment_favorites.*
import org.jetbrains.anko.doAsync

/**
 * A simple [Fragment] subclass.
 *
 */
class FavoritesFragment : Fragment() {

    // Private variables for our Views.
    private var wikiManager: WikiManager? = null
    var favoritesRecycler: RecyclerView? = null
    private var adapter: ArticleCardRecyclerAdapter = ArticleCardRecyclerAdapter()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        wikiManager = (activity?.applicationContext as WikiApplication).wikiManager
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_favorites, container, false)

        favoritesRecycler = view.findViewById<RecyclerView>(R.id.favorites_article_recycler)
        favoritesRecycler!!.layoutManager = LinearLayoutManager(context)
        favoritesRecycler!!.adapter = adapter

        return view
    }

    // Get the Favorites from the WikiManager & add them into the Adapter.
    override fun onResume() {
        super.onResume()

        // Use Anko's helpers to do things Asynchronous
        doAsync {
            val favoriteArticles = wikiManager!!.getFavorites()
            adapter.currentResults.clear()
            adapter.currentResults.addAll(favoriteArticles as ArrayList<WikiPage>)
            activity?.runOnUiThread { adapter.notifyDataSetChanged() }
        }
    }
}
