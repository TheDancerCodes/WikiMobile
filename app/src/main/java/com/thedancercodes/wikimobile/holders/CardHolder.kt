package com.thedancercodes.wikimobile.holders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.thedancercodes.wikimobile.R


class CardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val articleImageView: ImageView = itemView.findViewById<ImageView>(R.id.article_image)
    private val titleTextView: TextView = itemView.findViewById<TextView>(R.id.article_title)
}