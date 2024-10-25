package com.scottandmarc.opendotareborn.app.data.hero.info

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HeroInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHeroInfo(heroInfo: LocalHeroInfo)

    @Query("DELETE FROM heroes_info")
    fun deleteHeroesInfo()

    @Query("SELECT * FROM heroes_info")
    fun getHeroesInfo(): List<LocalHeroInfo>

    @Query("SELECT COUNT(*) FROM heroes_info")
    fun countHeroesInfo(): Int

    @Query("SELECT * FROM heroes_info WHERE id = :id")
    fun getHeroesInfoWhere(id: Int): LocalHeroInfo
}