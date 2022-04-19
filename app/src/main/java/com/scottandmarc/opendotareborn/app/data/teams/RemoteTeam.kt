package com.scottandmarc.opendotareborn.app.data.teams

import com.google.gson.annotations.SerializedName

data class RemoteTeam(
    @SerializedName("team_id")
    val teamId: Int,

    @SerializedName("rating")
    val rating: Float,

    @SerializedName("wins")
    val wins: Int,

    @SerializedName("losses")
    val losses: Int,

    @SerializedName("last_match_time")
    val lastMachTime: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("tag")
    val tag: String,

    @SerializedName("logo_url")
    val logoURL: String?
)