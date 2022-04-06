package com.scottandmarc.opendotareborn.app.data.matches

import com.google.gson.annotations.SerializedName

data class RemoteMatch(
    @SerializedName("match_id")
    val matchId: String,

    @SerializedName("player_slot")
    val playerSlot: Int,

    @SerializedName("radiant_win")
    val radiantWin: Boolean,

    @SerializedName("duration")
    val duration: Int,

    @SerializedName("game_mode")
    val gameMode: Int,

    @SerializedName("lobby_type")
    val lobbyType: Int,

    @SerializedName("hero_id")
    val heroId: Int,

    @SerializedName("start_time")
    val startTime: Int,

    @SerializedName("version")
    val version: Int?,

    @SerializedName("kills")
    val kills: Int,

    @SerializedName("deaths")
    val deaths: Int,

    @SerializedName("assists")
    val assists: Int,

    @SerializedName("skill")
    val skill: Int?,

    @SerializedName("leaver_status")
    val leaverStatus: Int,

    @SerializedName("party_size")
    val partySize: Int?,
)