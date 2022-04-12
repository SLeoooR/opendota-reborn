package com.scottandmarc.opendotareborn.app.data.peers

import com.google.gson.annotations.SerializedName

data class RemotePeer(
    @SerializedName("account_id")
    val accountId: Int,

    @SerializedName("last_played")
    val lastPlayed: Int,

    @SerializedName("win")
    val win: Int,

    @SerializedName("games")
    val games: Int,

    @SerializedName("with_win")
    val withWin: Int,

    @SerializedName("with_games")
    val withGames: Int,

    @SerializedName("against_win")
    val againstWin: Int,

    @SerializedName("against_games")
    val againstGames: Int,

    @SerializedName("with_gpm_sum")
    val withGPMSum: Int,

    @SerializedName("with_xpm_sum")
    val withXPMSum: Int,

    @SerializedName("personaname")
    val personaname: String,

    @SerializedName("name")
    val name: String?,

    @SerializedName("is_contributor")
    val isContributor: Boolean,

    @SerializedName("last_login")
    val lastLogin: String?,

    @SerializedName("avatar")
    val avatar: String,

    @SerializedName("avatarfull")
    val avatarfull: String
)