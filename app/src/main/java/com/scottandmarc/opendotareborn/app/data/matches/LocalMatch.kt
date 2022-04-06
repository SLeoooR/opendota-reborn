package com.scottandmarc.opendotareborn.app.data.matches

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matches")
data class LocalMatch(
    @PrimaryKey
    @ColumnInfo(name = "match_id")
    val matchId: String,

    @ColumnInfo(name = "player_slot")
    val playerSlot: Int,

    @ColumnInfo(name = "radiant_win")
    val radiantWin: Boolean,

    @ColumnInfo(name = "duration")
    val duration: Int,

    @ColumnInfo(name = "game_mode")
    val gameMode: Int,

    @ColumnInfo(name = "lobby_type")
    val lobbyType: Int,

    @ColumnInfo(name = "hero_id")
    val heroId: Int,

    @ColumnInfo(name = "start_time")
    val startTime: Int,

    @ColumnInfo(name = "version")
    val version: Int?,

    @ColumnInfo(name = "kills")
    val kills: Int,

    @ColumnInfo(name = "deaths")
    val deaths: Int,

    @ColumnInfo(name = "assists")
    val assists: Int,

    @ColumnInfo(name = "skill")
    val skill: Int?,

    @ColumnInfo(name = "leaver_status")
    val leaverStatus: Int,

    @ColumnInfo(name = "party_size")
    val partySize: Int?,
)