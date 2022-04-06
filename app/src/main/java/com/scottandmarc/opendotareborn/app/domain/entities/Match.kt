package com.scottandmarc.opendotareborn.app.domain.entities

data class Match(
    val matchId: String,
    val playerSlot: Int,
    val radiantWin: Boolean,
    val duration: Int,
    val gameMode: Int,
    val lobbyType: Int,
    val heroId: Int,
    val startTime: Int,
    val version: Int?,
    val kills: Int,
    val deaths: Int,
    val assists: Int,
    val skill: Int?,
    val leaverStatus: Int,
    val partySize: Int?,
)