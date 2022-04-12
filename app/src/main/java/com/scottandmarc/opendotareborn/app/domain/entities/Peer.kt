package com.scottandmarc.opendotareborn.app.domain.entities

data class Peer(
    val accountId: Int,
    val lastPlayed: Int,
    val win: Int,
    val games: Int,
    val withWin: Int,
    val withGames: Int,
    val againstWin: Int,
    val againstGames: Int,
    val withGPMSum: Int,
    val withXPMSum: Int,
    val personaname: String,
    val name: String?,
    val isContributor: Boolean,
    val lastLogin: String?,
    val avatar: String,
    val avatarFull: String
)