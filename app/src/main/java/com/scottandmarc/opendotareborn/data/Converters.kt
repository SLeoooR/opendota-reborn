package com.scottandmarc.opendotareborn.data

import androidx.room.TypeConverter
import com.scottandmarc.opendotareborn.data.player.MMREstimate
import com.scottandmarc.opendotareborn.data.player.Profile
import org.json.JSONObject

class Converters {
    @TypeConverter
    fun fromProfile(profile: Profile): String {
        return JSONObject().apply {
            put("account_id", profile.accountId)
            put("personaname", profile.personaName)
            put("name", profile.name)
            put("plus", profile.plus)
            put("cheese", profile.cheese)
            put("steamid", profile.steamId)
            put("avatar", profile.avatar)
            put("avatarmedium", profile.avatarMedium)
            put("avatarfull", profile.avatarFull)
            put("profileurl", profile.profileURL)
            put("last_login", profile.lastLogin)
            put("loccountrycode", profile.locCountryCode)
            put("is_contributor", profile.isContributor)
        }.toString()
    }

    @TypeConverter
    fun toProfile(profile: String): Profile {
        val json = JSONObject(profile)
        return Profile(
            json.getInt("account_id"),
            json.getString("personaname"),
            json.getString("name"),
            json.getBoolean("plus"),
            json.getInt("cheese"),
            json.getString("steamid"),
            json.getString("avatar"),
            json.getString("avatarmedium"),
            json.getString("avatarfull"),
            json.getString("profileurl"),
            json.getString("last_login"),
            json.getString("loccountrycode"),
            json.getBoolean("is_contributor"),
        )
    }

    @TypeConverter
    fun fromMMREstimate(mmrEstimate: MMREstimate): String {
        return JSONObject().apply {
            put("estimate", mmrEstimate.estimate)
        }.toString()
    }

    @TypeConverter
    fun toMMREstimate(mmrEstimate: String): MMREstimate {
        val json = JSONObject(mmrEstimate)
        return MMREstimate(
            json.getInt("estimate")
        )
    }
}