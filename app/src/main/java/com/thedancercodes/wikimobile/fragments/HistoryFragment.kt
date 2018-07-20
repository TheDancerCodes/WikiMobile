package com.thedancercodes.wikimobile.fragments


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*

import com.thedancercodes.wikimobile.R
import com.thedancercodes.wikimobile.WikiApplication
import com.thedancercodes.wikimobile.adapters.ArticleListItemRecyclerAdapter
import com.thedancercodes.wikimobile.managers.WikiManager
import com.thedancercodes.wikimobile.models.WikiPage
import kotlinx.android.synthetic.main.fragment_history.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton

/**
 * A simple [Fragment] subclass.
 *
 */
class HistoryFragment : Fragment() {

    // Private variables for our Views.
    private var wikiManager: WikiManager? = null
    var historyRecycler: RecyclerView? = null
    private val adapter = ArticleListItemRecyclerAdapter()

    // To ensure the Clear toolbar option is visible
    init {
        setHasOptionsMenu(true)
    }

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
        historyRecycler!!.adapter = adapter

        return view
    }

    // Get the History from the WikiManager & add them into the Adapter.
    override fun onResume() {
        super.onResume()

        // Use Anko's helpers to do things Asynchronous
        doAsync {
            val history = wikiManager!!.getHistory()
            adapter.currentResults.clear()
            adapter.currentResults.addAll(history as ArrayList<WikiPage>)
            activity?.runOnUiThread { adapter.notifyDataSetChanged() }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater!!.inflate(R.menu.history_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.action_clear_history){

            // Show confirmation alert
            activity?.alert("Are you sure you want to clear your history?", "Confirm"){
                yesButton {

                    // Yes was hit
                    // Clear History async
                    adapter.currentResults.clear()
                    doAsync {
                        wikiManager?.clearHistory()
                    }
                    activity?.runOnUiThread{ adapter.notifyDataSetChanged() }
                }
                noButton {
                    // Do something here if you want, but we don't have to
                }
            }?.show()
        }
        return true
    }
}
