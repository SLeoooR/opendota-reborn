package com.scottandmarc.opendotareborn.app.data.hero.info

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class HeroInfoDeserializer : JsonDeserializer<List<RemoteHeroInfo>> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<RemoteHeroInfo> {

        val response = json.asJsonObject
        Log.d("response", "$response")
        val remoteHeroesInfo = response.asJsonObject

        val gson = GsonBuilder()
            .create()

        return gson.fromJson(remoteHeroesInfo, Array<RemoteHeroInfo>::class.java).toList()
    }
}