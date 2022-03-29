package com.scottandmarc.opendotareborn.app.domain.gateways

import com.scottandmarc.opendotareborn.app.data.hero.RemoteHeroes

interface HeroesGateway {
    // API Method
    suspend fun fetchHeroes(
        accountId: Int?,
    ): List<RemoteHeroes.RemoteHero>
}