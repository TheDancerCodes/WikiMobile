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
import com.thedancercodes.wikimobile.adapters.ArticleListItemRecyclerAdapter
import com.thedancercodes.wikimobile.managers.WikiManager
import kotlinx.android.synthetic.main.fragment_history.*

/**
 * A simple [Fragment] subclass.
 *
 */
class HistoryFragment : Fragment() {

    // Private variables for our Views.
    private var wikiManager: WikiManager? = null
    var historyRecycler: RecyclerView? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        wikiManager = (activity?.applicationContext as WikiApplication).wikiManager
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_history, container, false)

        historyRecycler = view.findViewById<RecyclerView>(R.id.history_article_recycler)
        historyRecycler!!.layoutManager = LinearLayoutManager(context)
        historyRecycler!!.adapter = ArticleListItemRecyclerAdapter()

        return view
    }


}
