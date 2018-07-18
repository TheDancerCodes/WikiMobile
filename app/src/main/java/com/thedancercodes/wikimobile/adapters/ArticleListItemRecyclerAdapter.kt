package com.thedancercodes.wikimobile.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.thedancercodes.wikimobile.R
import com.thedancercodes.wikimobile.holders.CardHolder
import com.thedancercodes.wikimobile.holders.ListItemHolder
import com.thedancercodes.wikimobile.models.WikiPage


class ArticleListItemRecyclerAdapter : RecyclerView.Adapter<ListItemHolder>(){

    /*
        Collection of WikiPages representing our data-set.

        This ArrayList of WikiPages will be set within the Fragment or Activity that's updating with
        the data.
    */
    val currentResults: ArrayList<WikiPage> = ArrayList<WikiPage>()

    // The number of items that our RecyclerView will contain
    override fun getItemCount(): Int {
        return currentResults.size // Returns size of the ArrayList
    }

    // How we update the ViewHolder's content with new content from our page
    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {

        // Grab page from Current Result
        var page = currentResults[position]

        // Update View within Holder.
        holder?.updateWithPage(page)
    }

    // Create the ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {
        var cardItem = LayoutInflater.from(parent?.context)
                .inflate(R.layout.article_list_item, parent, false)
        return ListItemHolder(cardItem)
    }
}