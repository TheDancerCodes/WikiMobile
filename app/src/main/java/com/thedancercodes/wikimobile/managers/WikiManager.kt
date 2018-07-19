package com.thedancercodes.wikimobile.managers

import com.thedancercodes.wikimobile.models.WikiPage
import com.thedancercodes.wikimobile.models.WikiResult
import com.thedancercodes.wikimobile.providers.ArticleDataProvider
import com.thedancercodes.wikimobile.repositories.FavoritesRepository
import com.thedancercodes.wikimobile.repositories.HistoryRepository

/* A Manager class to handle both communicating to our Repositories and our DataProvider */
class WikiManager(private val provider: ArticleDataProvider,
                  private val favoritesRepository: FavoritesRepository,
                  private val historyRepository: HistoryRepository) {

    private var favoritesCache: ArrayList<WikiPage>? = null
    private var historyCache: ArrayList<WikiPage>? = null

    fun search(term: String, skip: Int, take: Int, handler: (result: WikiResult) -> Unit?) {
        return provider.search(term, skip, take, handler)
    }

    fun getRandom(take: Int, handler: (result: WikiResult) -> Unit?) {
        return provider.getRandom(take, handler)
    }

    fun getHistory(): ArrayList<WikiPage>? {
        if (historyCache == null)
            historyCache = historyRepository.getAllHistory()
        return historyCache
    }

    fun getFavorites(): ArrayList<WikiPage>? {
        if (favoritesCache == null)
            favoritesCache = favoritesRepository.getAllFavorites()
        return favoritesCache
    }

    fun addFavorite(page: WikiPage) {
        favoritesCache?.add(page)
        favoritesRepository.addFavorite(page)
    }

    fun removeFavorite(pageId: Int) {
        favoritesRepository.removeFavoriteById(pageId)
        favoritesCache = favoritesCache!!.filter { it.pageid != pageId } as ArrayList<WikiPage>
    }

    fun getIsFavorite(pageId: Int): Boolean{
        return favoritesRepository.isArticleFavorite(pageId)
    }

    fun addHistory(page: WikiPage) {
        historyCache?.add(page)
        historyRepository.addFavorite(page)
    }

    fun clearHistory() {
        historyCache?.clear()
        val allHistory = historyRepository.getAllHistory()
        allHistory?.forEach { historyRepository.removePageById(it.pageid!!) }
    }

}