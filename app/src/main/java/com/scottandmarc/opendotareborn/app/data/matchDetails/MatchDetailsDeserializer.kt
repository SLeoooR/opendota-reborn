package com.scottandmarc.opendotareborn.app.data.matchDetails

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class MatchDetailsDeserializer : JsonDeserializer<RemoteMatchDetails> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): RemoteMatchDetails {

        val response = json.asJsonObject
        Log.d("response", "$response")
        val remoteMatches = response.asJsonObject

        val gson = GsonBuilder()
            .create()

        return gson.fromJson(remoteMatches, RemoteMatchDetails::class.java)
    }
}