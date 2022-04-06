package com.scottandmarc.opendotareborn.app.data.matches

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class MatchDeserializer : JsonDeserializer<List<RemoteMatch>> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<RemoteMatch> {

        val response = json.asJsonObject
        Log.d("response", "$response")
        val remoteMatches = response.asJsonObject

        val gson = GsonBuilder()
            .create()

        return gson.fromJson(remoteMatches, Array<RemoteMatch>::class.java).toList()
    }
}