package com.thedancercodes.wikimobile.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.thedancercodes.wikimobile.R
import com.thedancercodes.wikimobile.holders.CardHolder
import com.thedancercodes.wikimobile.holders.ListItemHolder


class ArticleListItemRecyclerAdapter : RecyclerView.Adapter<ListItemHolder>(){

    // The number of items that our RecyclerView will contain
    override fun getItemCount(): Int {
        return 15 // temporary
    }

    // How we update the ViewHolder's content with new content from our page
    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {

        // This is where we will update our View.
    }

    // Create the ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {
        var cardItem = LayoutInflater.from(parent?.context)
                .inflate(R.layout.article_list_item, parent, false)
        return ListItemHolder(cardItem)
    }
}