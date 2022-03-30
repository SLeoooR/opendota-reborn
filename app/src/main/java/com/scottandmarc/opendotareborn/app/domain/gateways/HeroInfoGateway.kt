package com.scottandmarc.opendotareborn.app.domain.gateways

import com.scottandmarc.opendotareborn.app.data.hero.info.LocalHeroInfo
import com.scottandmarc.opendotareborn.app.domain.entities.HeroInfo

interface HeroInfoGateway {
    // API Method
    suspend fun fetchHeroesInfo(): List<HeroInfo>

    // Room Methods
    fun insertHeroInfo(heroInfo: HeroInfo)
    fun deleteHeroesInfo()
    fun getHeroesInfo(): List<HeroInfo>
    fun countHeroesInfo(): Int
    fun getHeroInfoWhere(id: Int): HeroInfo
}