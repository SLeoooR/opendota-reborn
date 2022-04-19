package com.scottandmarc.opendotareborn.app.data.teams

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class TeamDeserializer : JsonDeserializer<List<RemoteTeam>> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<RemoteTeam> {

        val response = json.asJsonObject
        Log.d("response", "$response")
        val remoteTeams = response.asJsonObject

        val gson = GsonBuilder()
            .create()

        return gson.fromJson(remoteTeams, Array<RemoteTeam>::class.java).toList()
    }
}