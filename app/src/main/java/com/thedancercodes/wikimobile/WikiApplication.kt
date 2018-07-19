package com.thedancercodes.wikimobile

import android.app.Application
import com.thedancercodes.wikimobile.managers.WikiManager
import com.thedancercodes.wikimobile.providers.ArticleDataProvider
import com.thedancercodes.wikimobile.repositories.ArticleDatabaseOpenHelper
import com.thedancercodes.wikimobile.repositories.FavoritesRepository
import com.thedancercodes.wikimobile.repositories.HistoryRepository

/**
 * Created by TheDancerCodes on 19/07/2018.
 */
class WikiApplication: Application() {

    private var dbHelper: ArticleDatabaseOpenHelper? = null
    private var favoritesRepository: FavoritesRepository? = null
    private var historyRepository: HistoryRepository? = null
    private var wikiProvider: ArticleDataProvider? = null
    var wikiManager: WikiManager? = null
        private set // To secure the WikiManager and ensure no other classes are changing it.

    override fun onCreate() {
        super.onCreate()

        // Instantiate the Private Properties - NB: Order Matters
        dbHelper = ArticleDatabaseOpenHelper(applicationContext)
        favoritesRepository = FavoritesRepository(dbHelper!!)
        historyRepository = HistoryRepository(dbHelper!!)
        wikiProvider = ArticleDataProvider()
        wikiManager = WikiManager(wikiProvider!!, favoritesRepository!!, historyRepository!!)
    }

}