package com.scottandmarc.opendotareborn.app.data.recentMatches

import com.google.gson.annotations.SerializedName

data class RemoteRecentMatch(
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

    @SerializedName("xp_per_min")
    val xpPerMin: Int,

    @SerializedName("gold_per_min")
    val goldPerMin: Int,

    @SerializedName("hero_damage")
    val heroDamage: Int,

    @SerializedName("tower_damage")
    val towerDamage: Int,

    @SerializedName("hero_healing")
    val heroHealing: Int,

    @SerializedName("last_hits")
    val lastHits: Int,

    @SerializedName("lane")
    val lane: Int,

    @SerializedName("lane_role")
    val laneRole: Int,

    @SerializedName("is_roaming")
    val isRoaming: Boolean,

    @SerializedName("cluster")
    val cluster: Int,

    @SerializedName("leaver_status")
    val leaverStatus: Int,

    @SerializedName("party_size")
    val partySize: Int?,
)