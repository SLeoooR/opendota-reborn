package com.scottandmarc.opendotareborn.app.domain.gateways

import com.scottandmarc.opendotareborn.app.domain.entities.PlayerHero

interface PlayerHeroGateway {
    // API Method
    suspend fun fetchHeroes(
        accountId: Int,
    ): List<PlayerHero>
}