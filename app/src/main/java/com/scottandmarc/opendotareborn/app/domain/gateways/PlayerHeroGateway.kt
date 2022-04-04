package com.scottandmarc.opendotareborn.app.domain.gateways

import com.scottandmarc.opendotareborn.app.data.hero.player.RemotePlayerHero
import com.scottandmarc.opendotareborn.app.domain.entities.HeroInfo
import com.scottandmarc.opendotareborn.app.domain.entities.PlayerHero

interface PlayerHeroGateway {
    // API Method
    suspend fun fetchHeroes(
        accountId: Int?,
    ): List<PlayerHero>

    // Room Methods
    fun insertPlayerHero(playerHero: PlayerHero)
    fun deletePlayerHeroes()
    fun getPlayerHeroes(): List<PlayerHero>
    fun countPlayerHeroes(): Int
    fun getPlayerHeroWhere(id: Int): PlayerHero
}