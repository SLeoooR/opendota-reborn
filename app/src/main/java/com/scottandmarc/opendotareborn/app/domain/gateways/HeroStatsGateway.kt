package com.scottandmarc.opendotareborn.app.domain.gateways

import com.scottandmarc.opendotareborn.app.domain.entities.HeroStats

interface HeroStatsGateway {
    // API Method
    suspend fun fetchHeroesStats(): List<HeroStats>
}