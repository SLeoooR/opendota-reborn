package com.scottandmarc.opendotareborn.app.data.player

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlayerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(player: LocalPlayer)

    @Query("DELETE FROM players")
    fun delete()

    @Query("SELECT * FROM players")
    fun getPlayer(): LocalPlayer

    @Query("SELECT COUNT(*) FROM players")
    fun count(): Int
}