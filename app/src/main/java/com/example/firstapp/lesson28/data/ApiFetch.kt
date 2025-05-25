package com.example.firstapp.lesson28.data

import com.example.firstapp.lesson28.domain.ArtFact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class ApiFetch {
        suspend fun fetchFacts() : List<ArtFact> {
            return withContext(Dispatchers.IO) {
                //val url = URL("https://meowfacts.herokuapp.com/?count=3")
                val url = URL("https://api.artic.edu/api/v1/artworks")
                (url.openConnection() as HttpsURLConnection).run {
                    requestMethod = "GET"
                    try {
                        if (responseCode == 200) {
                            val response = inputStream.bufferedReader().use { it.readText() }
                            parseData(response)
                        } else {
                            emptyList()
                        }
                    } finally {
                        disconnect()
                    }
                }
            }
        }

        private fun parseData(json: String): List<ArtFact> {
            val jsonObject = JSONObject(json)
            val jsonArray = jsonObject.getJSONArray("data")

            val items = mutableListOf<ArtFact>()
            for (i in 0 until jsonArray.length()) {
                val itemObj = jsonArray.getJSONObject(i)
                items.add(
                    ArtFact(
                        id = itemObj.getString("id"),
                        title = itemObj.getString("title")
                    )
                )
            }
            return items
        }

}