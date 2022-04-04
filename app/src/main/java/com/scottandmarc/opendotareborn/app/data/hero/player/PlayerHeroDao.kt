package com.scottandmarc.opendotareborn.app.data.hero.player

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlayerHeroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlayerHero(playerHero: LocalPlayerHero)

    @Query("DELETE FROM player_heroes")
    fun deletePlayerHeroes()

    @Query("SELECT * FROM player_heroes")
    fun getPlayerHeroes(): List<LocalPlayerHero>

    @Query("SELECT COUNT(*) FROM player_heroes")
    fun countPlayerHeroes(): Int

    @Query("SELECT * FROM player_heroes WHERE hero_id = :heroId")
    fun getPlayerHeroWhere(heroId: Int): LocalPlayerHero
}