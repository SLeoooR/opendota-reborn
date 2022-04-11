package com.scottandmarc.opendotareborn.app.data.recentMatches

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class RecentMatchDeserializer : JsonDeserializer<List<RemoteRecentMatch>> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<RemoteRecentMatch> {

        val response = json.asJsonObject
        Log.d("response", "$response")
        val remoteRecentMatches = response.asJsonObject

        val gson = GsonBuilder()
            .create()

        return gson.fromJson(remoteRecentMatches, Array<RemoteRecentMatch>::class.java).toList()
    }
}