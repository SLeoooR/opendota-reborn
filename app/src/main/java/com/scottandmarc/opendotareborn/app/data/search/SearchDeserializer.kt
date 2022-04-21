package com.scottandmarc.opendotareborn.app.data.search

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class SearchDeserializer : JsonDeserializer<List<RemoteSearch>> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<RemoteSearch> {

        val response = json.asJsonObject
        Log.d("response", "$response")
        val remoteSearch = response.asJsonObject

        val gson = GsonBuilder()
            .create()

        return gson.fromJson(remoteSearch, Array<RemoteSearch>::class.java).toList()
    }
}