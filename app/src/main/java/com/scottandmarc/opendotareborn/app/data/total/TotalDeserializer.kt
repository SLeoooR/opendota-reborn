package com.scottandmarc.opendotareborn.app.data.total

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class TotalDeserializer : JsonDeserializer<List<RemoteTotal>> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<RemoteTotal> {

        val response = json.asJsonObject
        Log.d("response", "$response")
        val remoteTotals = response.asJsonObject

        val gson = GsonBuilder()
            .create()

        return gson.fromJson(remoteTotals, Array<RemoteTotal>::class.java).toList()
    }
}