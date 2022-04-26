package com.scottandmarc.opendotareborn.app.data.player

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "players")
data class LocalPlayer (
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "tracked_until")
    val trackedUntil: String?,

    @ColumnInfo(name = "solo_competitive_rank")
    val soloCompetitiveRank: Int?,

    @ColumnInfo(name = "competitive_rank")
    val competitiveRank: Int?,

    @ColumnInfo(name = "rank_tier")
    val rankTier: Int?,

    @ColumnInfo(name = "leaderboard_rank")
    val leaderboardRank: Int?,

    @Embedded
    val mmrEstimate: LocalMMREstimate?,

    @Embedded
    val profile: LocalProfile,

    @Embedded
    val winLose: LocalWinLose,
) {
    data class LocalMMREstimate (
        @ColumnInfo(name = "estimate")
        val estimate: Int?,
    )

    data class LocalProfile (
        @ColumnInfo(name = "account_id")
        val accountId: Int,

        @ColumnInfo(name = "personaname")
        val personaName: String,

        @ColumnInfo(name = "name")
        val name: String?,

        @ColumnInfo(name = "plus")
        val plus: Boolean?,

        @ColumnInfo(name = "cheese")
        val cheese: Int,

        @ColumnInfo(name = "steamid")
        val steamId: String,

        @ColumnInfo(name = "avatar")
        val avatar: String,

        @ColumnInfo(name = "avatarmedium")
        val avatarMedium: String,

        @ColumnInfo(name = "avatarfull")
        val avatarFull: String,

        @ColumnInfo(name = "profileurl")
        val profileURL: String,

        @ColumnInfo(name = "last_login")
        val lastLogin: String?,

        @ColumnInfo(name = "loccountrycode")
        val locCountryCode: String?,

        @ColumnInfo(name = "is_contributor")
        val isContributor: Boolean,
    )

    data class LocalWinLose(
        @ColumnInfo(name = "win")
        val win: Int,

        @ColumnInfo(name = "lose")
        val lose: Int,
    )
}

