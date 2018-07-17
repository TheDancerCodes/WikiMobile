package com.thedancercodes.wikimobile.models

/**
 *  Actual result we get from the API as a whole. (Wraps the entire rest of the query )
 *
 *  NB: We only add query as we wont use batch complete
 *
 *  See: <url>
 *
 *  https://en.wikipedia.org/w/api.php?format=json&action=query&formatversion=2
 *  &generator=random&grnnamespace=0&prop=pageimages|info&grnlimit=1&inprop=url
 *
 */
class WikiResult {
    val query: WikiQueryData? = null
}