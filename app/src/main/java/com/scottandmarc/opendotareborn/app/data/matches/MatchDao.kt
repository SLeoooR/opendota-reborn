package com.scottandmarc.opendotareborn.app.data.matches

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MatchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatch(localMatch: LocalMatch)

    @Query("DELETE FROM matches")
    fun deleteMatches()

    @Query("SELECT * FROM matches")
    fun getMatches(): List<LocalMatch>

    @Query("SELECT COUNT(*) FROM matches")
    fun countMatches(): Int

    @Query("SELECT * FROM matches WHERE match_id = :id")
    fun getMatch(id: Int): LocalMatch
}