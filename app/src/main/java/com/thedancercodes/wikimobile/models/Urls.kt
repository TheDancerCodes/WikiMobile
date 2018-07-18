package com.thedancercodes.wikimobile.models

/**
 * This model represents getting the URLs we need for our different queries.
 *
 * Use this object to create 2 different functions for the URLs we need.
 *
 */
object Urls {

    val BaseUrl = "https://en.wikipedia.org/w/api.php"

    // This function builds a string that we use to create our HTTP request to get the data for
    // searching for articles on Wikipedia.
    fun getSearchUrl(term: String, skip: Int, take: Int) : String{
        return BaseUrl + "?action=query" +
                "&formatversion=2" +
                "&generator=prefixsearch" +
                "&gpssearch=$term" +
                "&gpslimit=$take" +
                "&gpsoffset=$skip" +
                "&prop=pageimages|info" +
                "&piprop=thumbnail|url" +
                "&pithumbsize=200" +
                "&pilimit=$take" +
                "&wbptterms=description" +
                "&format=json" +
                "&inprop=url"

    }

    // This function builds a string that we use to create our HTTP request to get the data for
    // random articles on Wikipedia.
    fun getRandomUrl(take: Int) : String{
        return BaseUrl + "?action=query" +
                "&format=json" +
                "&formatversion=2" +
                "&generator=random" +
                "&grnnamespace=0" +
                "&prop=pageimages|info" +
                "&grnlimit=$take" +
                "&inprop=url" +
                "&pithumbsize=200"
    }
}