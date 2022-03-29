package com.scottandmarc.opendotareborn.app.data.hero

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class HeroesDeserializer : JsonDeserializer<List<RemoteHeroes.RemoteHero>> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<RemoteHeroes.RemoteHero> {

        val response = json.asJsonObject
        Log.d("response", "$response")
        val remoteHeroes = response.asJsonObject

        val gson = GsonBuilder()
            .create()

        return gson.fromJson(remoteHeroes, Array<RemoteHeroes.RemoteHero>::class.java).toList()
    }
}