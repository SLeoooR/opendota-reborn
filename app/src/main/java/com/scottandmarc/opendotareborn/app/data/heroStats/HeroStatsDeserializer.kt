package com.scottandmarc.opendotareborn.app.data.heroStats

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class HeroStatsDeserializer : JsonDeserializer<List<RemoteHeroStats>> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<RemoteHeroStats> {

        val response = json.asJsonObject
        Log.d("response", "$response")
        val remoteHeroStats = response.asJsonObject

        val gson = GsonBuilder()
            .create()

        return gson.fromJson(remoteHeroStats, Array<RemoteHeroStats>::class.java).toList()
    }
}