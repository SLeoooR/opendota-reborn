package com.scottandmarc.opendotareborn.app.data.hero.player

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class PlayerHeroDeserializer : JsonDeserializer<List<RemotePlayerHero>> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<RemotePlayerHero> {

        val response = json.asJsonObject
        Log.d("response", "$response")
        val remotePlayerHeroes = response.asJsonObject

        val gson = GsonBuilder()
            .create()

        return gson.fromJson(remotePlayerHeroes, Array<RemotePlayerHero>::class.java).toList()
    }
}