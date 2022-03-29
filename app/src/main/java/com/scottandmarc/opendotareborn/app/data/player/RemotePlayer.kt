package com.scottandmarc.opendotareborn.app.data.player

import com.google.gson.annotations.SerializedName

data class RemotePlayer(
    @SerializedName("tracked_until")
    val trackedUntil: String?,

    @SerializedName("solo_competitive_rank")
    val soloCompetitiveRank: Int?,

    @SerializedName("competitive_rank")
    val competitiveRank: Int?,

    @SerializedName("rank_tier")
    val rankTier: Int?,

    @SerializedName("leaderboard_rank")
    val leaderboardRank: Int?,

    @SerializedName("mmr_estimate")
    val mmrEstimate: RemoteMMREstimate?,

    @SerializedName("profile")
    val profile: RemoteProfile?,
) {
    data class RemoteMMREstimate (
        @SerializedName("estimate")
        val estimate: Int?,
    )

    data class RemoteProfile (
        @SerializedName("account_id")
        val accountId: Int?,

        @SerializedName("personaname")
        val personaName: String?,

        @SerializedName("name")
        val name: String?,

        @SerializedName("plus")
        val plus: Boolean?,

        @SerializedName("cheese")
        val cheese: Int?,

        @SerializedName("steamid")
        val steamId: String?,

        @SerializedName("avatar")
        val avatar: String?,
        @SerializedName("avatarmedium")
        val avatarMedium: String?,

        @SerializedName("avatarfull")
        val avatarFull: String?,

        @SerializedName("profileurl")
        val profileURL: String?,

        @SerializedName("last_login")
        val lastLogin: String?,

        @SerializedName("loccountrycode")
        val locCountryCode: String?,

        @SerializedName("is_contributor")
        val isContributor: Boolean?,
    )
}

