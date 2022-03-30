package com.scottandmarc.opendotareborn.app.domain.entities

data class PlayerHero(
    val heroId: String,
    val lastPlayed: Int,
    val games: Int,
    val win: Int,
    val withGames: Int,
    val withWin: Int,
)