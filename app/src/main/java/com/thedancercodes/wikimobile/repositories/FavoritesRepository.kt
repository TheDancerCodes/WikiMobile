package com.thedancercodes.wikimobile.repositories

import com.google.gson.Gson
import com.thedancercodes.wikimobile.models.WikiPage
import com.thedancercodes.wikimobile.models.WikiThumbnail
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.rowParser
import org.jetbrains.anko.db.select


// Stores & Accesses Data from Favorites table
class FavoritesRepository(val databaseHelper: ArticleDatabaseOpenHelper) {

    // TABLE_NAME Constant
    private val TABLE_NAME: String = "Favorites"

    fun addFavorite(page: WikiPage) {
        databaseHelper.use {
            insert(TABLE_NAME,
                    "id" to page.pageid,
                    "title" to page.title,
                    "url" to page.fullurl,
                    "thumbnailJson" to Gson().toJson(page.thumbnail))
        }
    }

    fun removeFavoriteById(pageId: Int) {
        databaseHelper.use {
            delete(TABLE_NAME, "id = {pageId}", "pageId" to pageId)
        }
    }

    fun isArticleFavorite(pageId: Int): Boolean {

        // Get Favorites and Filter
        var pages = getAllFavorites()

        // return only the pages that have the ID of pages passed in.
        return pages.any { page ->
            page.pageid == pageId
        }
    }

    fun getAllFavorites(): ArrayList<WikiPage> {

        var pages = ArrayList<WikiPage>()

        // Tells Anko how to map the row from the DB to a proper model
        val articleRowParser = rowParser { id: Int, title: String, url: String, thumbnailJson: String ->

            val page = WikiPage()
            page.title = title
            page.pageid = id
            page.fullurl = url
            page.thumbnail = Gson().fromJson(thumbnailJson, WikiThumbnail::class.java)

            pages.add(page)
        }

        databaseHelper.use {
            select(TABLE_NAME).parseList(articleRowParser)
        }

        return pages
    }
}