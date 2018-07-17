package com.thedancercodes.wikimobile.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.thedancercodes.wikimobile.R
import com.thedancercodes.wikimobile.activities.SearchActivity
import com.thedancercodes.wikimobile.adapters.ArticleCardRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_explore.*

/**
 * A simple [Fragment] subclass.
 *
 */
class ExploreFragment : Fragment() {

    // Private variables for our Views.
    var searchCardView: CardView? = null
    var exploreRecycler: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater!!.inflate(R.layout.fragment_explore, container, false)

        // Reference to the SearchCardView & RecyclerView
        searchCardView = view.findViewById<CardView>(R.id.search_card_view)
        exploreRecycler = view.findViewById<RecyclerView>(R.id.explore_article_recycler)

        searchCardView!!.setOnClickListener{

            // Set an Intent that goes to the SearchActivity
            val searchIntent = Intent(context, SearchActivity::class.java)
            context!!.startActivity(searchIntent)
        }

        // Wire up our adapter with our RecyclerView
        exploreRecycler!!.layoutManager = LinearLayoutManager(context)
        exploreRecycler!!.adapter = ArticleCardRecyclerAdapter()

        return view
    }

    // Wire up clicking the Card View & having it navigate to the SearchActivity

} // Required empty public constructor
