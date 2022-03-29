package com.scottandmarc.opendotareborn.app.domain.entities

data class Player (
    val trackedUntil: String?,
    val soloCompetitiveRank: Int?,
    val competitiveRank: Int?,
    val rankTier: Int?,
    val leaderboardRank: Int?,
    val mmrEstimate: MMREstimate?,
    val profile: Profile?,
    val winLose: WinLose,
) {
    data class MMREstimate (
        val estimate: Int?,
    )

    data class Profile (
        val accountId: Int?,
        val personaName: String?,
        val name: String?,
        val plus: Boolean?,
        val cheese: Int?,
        val steamId: String?,
        val avatar: String?,
        val avatarMedium: String?,
        val avatarFull: String?,
        val profileURL: String?,
        val lastLogin: String?,
        val locCountryCode: String?,
        val isContributor: Boolean?,
    )

    data class WinLose(
        val id: Int?,
        val win: Int,
        val lose: Int
    )
}