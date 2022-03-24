package com.scottandmarc.opendotareborn.app.data.player

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class PlayerDeserializer : JsonDeserializer<RemotePlayer> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): RemotePlayer {

        val response = json.asJsonObject
        Log.d("response", "$response")
        val remotePlayer = response.asJsonObject

        val gson = GsonBuilder()
            .create()

        return gson.fromJson(remotePlayer, RemotePlayer::class.java)
    }
}