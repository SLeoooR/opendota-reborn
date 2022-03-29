package com.scottandmarc.opendotareborn.app.domain.entities

data class Heroes(
    val heroesList: List<Hero>
) {
    data class Hero(
        val heroId: String,
        val lastPlayed: Int,
        val games: Int,
        val win: Int,
        val withGames: Int,
        val withWin: Int,
    )
}