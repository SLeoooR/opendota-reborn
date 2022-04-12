package com.scottandmarc.opendotareborn.app.data.peers

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class PeerDeserializer : JsonDeserializer<List<RemotePeer>> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<RemotePeer> {

        val response = json.asJsonObject
        Log.d("response", "$response")
        val remotePeers = response.asJsonObject

        val gson = GsonBuilder()
            .create()

        return gson.fromJson(remotePeers, Array<RemotePeer>::class.java).toList()
    }
}