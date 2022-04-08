package com.scottandmarc.opendotareborn.app.data.hero.player

import android.util.Log
import com.scottandmarc.opendotareborn.app.domain.entities.PlayerHero
import com.scottandmarc.opendotareborn.app.domain.gateways.PlayerHeroGateway

class PlayerHeroRepository(
    private val playerHeroesService: PlayerHeroesEndpoints,
) : PlayerHeroGateway {
    override suspend fun fetchHeroes(accountId: Int): List<PlayerHero> {
        return try {
            val fetchedHeroes = playerHeroesService.fetchPlayerHeroes(accountId).body()!!

            fun RemotePlayerHero.toDomain(): PlayerHero {
                return PlayerHero(
                    this.heroId,
                    this.lastPlayed,
                    this.games,
                    this.win,
                    this.withGames,
                    this.withWin
                )
            }

            fetchedHeroes.map {
                it.toDomain()
            }
        } catch (e: Exception) {
            Log.d("error", e.localizedMessage?: "")
            throw e
        }
    }
}