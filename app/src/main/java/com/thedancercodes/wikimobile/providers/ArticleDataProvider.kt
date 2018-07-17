package com.thedancercodes.wikimobile.providers

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.httpGet
import com.google.gson.Gson
import com.thedancercodes.wikimobile.models.Urls
import com.thedancercodes.wikimobile.models.WikiResult
import java.io.Reader

/**
 *  Class for getting the data from Wikipedia via HTTP.
 *
 *  Processes data from an external source.
 *
 *  This class wraps making HTTP requests and maps the data back to our models.
 */
class ArticleDataProvider {

    // Set the User-Agent Header
    init {
        FuelManager.instance.baseHeaders = mapOf("User-Agent" to "WikiMobile")
    }

    // Search Function
    fun search(term: String, skip: Int, take: Int, responseHandler: (result: WikiResult) -> Unit?) {
        Urls.getSearchUrl(term, skip, take).httpGet()
                .responseObject(WikipediaDataDeserializer()) { _, response, result ->

                    // Error Handling
                    if (response.statusCode != 200) {
                        throw Exception("Unable to GET articles")
                    }

                    // Calling our response handler
                    val (data, _) = result // This is a Kotlin pair object
                    responseHandler.invoke(data as WikiResult)


                }
    }

    // Random Function
    fun getRandom(take: Int, responseHandler: (result: WikiResult) -> Unit?) {
        Urls.getRandomUrl(take).httpGet()
                .responseObject(WikipediaDataDeserializer()) { _, response, result ->

                    // Error Handling
                    if (response.statusCode != 200) {
                        throw Exception("Unable to GET articles")
                    }

                    val (data, _) = result
                    responseHandler.invoke(data as WikiResult)
                }
    }

    /*
       Subclass for the Wikipedia Data Deserializer.

       (i) Allows Fuel to know what its deserializing the data with & what its using to do it.

      (ii) We create a deserializer that's going to use Gson to pass the String from
      the Reader down to the object that we need. <WikiResult>

    */
    class WikipediaDataDeserializer : ResponseDeserializable<WikiResult> {
        override fun deserialize(reader: Reader) = Gson().fromJson(reader, WikiResult::class.java)
    }
}