package com.thedancercodes.wikimobile.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.thedancercodes.wikimobile.R
import com.thedancercodes.wikimobile.adapters.ArticleCardRecyclerAdapter
import com.thedancercodes.wikimobile.adapters.ArticleListItemRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_favorites.*

/**
 * A simple [Fragment] subclass.
 *
 */
class FavoritesFragment : Fragment() {

    // Private variables for our Views.
    var favoritesRecycler: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_favorites, container, false)

        favoritesRecycler = view.findViewById<RecyclerView>(R.id.favorites_article_recycler)
        favoritesRecycler!!.layoutManager = LinearLayoutManager(context)
        favoritesRecycler!!.adapter = ArticleCardRecyclerAdapter()

        return view
    }


}
