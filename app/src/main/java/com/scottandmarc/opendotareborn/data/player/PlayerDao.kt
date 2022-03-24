package com.scottandmarc.opendotareborn.data.player

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlayerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(player: Player)

    @Query("DELETE FROM players")
    fun delete()

    @Query("SELECT * FROM players")
    fun getPlayer(): Player

    @Query("SELECT COUNT(*) FROM players")
    fun count(): Int
}