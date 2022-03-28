package com.scottandmarc.opendotareborn.app.data.player

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WinLoseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(winLose: LocalWinLose)

    @Query("DELETE FROM win_lose")
    fun delete()

    @Query("SELECT * FROM win_lose")
    fun getWinLose(): LocalWinLose

    @Query("SELECT COUNT(*) FROM win_lose")
    fun count(): Int
}