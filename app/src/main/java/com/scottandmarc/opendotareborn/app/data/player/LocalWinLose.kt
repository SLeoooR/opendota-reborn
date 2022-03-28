package com.scottandmarc.opendotareborn.app.data.player

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "win_lose")
data class LocalWinLose(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int?,

    @ColumnInfo(name = "win")
    val win: Int,

    @ColumnInfo(name = "lose")
    val lose: Int,
)