package com.scottandmarc.opendotareborn.app.data.hero.player

import com.google.gson.annotations.SerializedName

data class RemotePlayerHero(
    @SerializedName("hero_id")
    val heroId: String,

    @SerializedName("last_played")
    val lastPlayed: Int,

    @SerializedName("games")
    val games: Int,

    @SerializedName("win")
    val win: Int,

    @SerializedName("with_games")
    val withGames: Int,

    @SerializedName("with_win")
    val withWin: Int,
)