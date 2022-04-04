package com.scottandmarc.opendotareborn.app.data.hero.player

import android.util.Log
import com.scottandmarc.opendotareborn.app.domain.entities.PlayerHero
import com.scottandmarc.opendotareborn.app.domain.gateways.PlayerHeroGateway

class PlayerHeroRepository(
    private val playerHeroDao: PlayerHeroDao,
    private val playerHeroesService: PlayerHeroesEndpoints,
) : PlayerHeroGateway {
    override suspend fun fetchHeroes(accountId: Int?): List<PlayerHero> {
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

    override fun insertPlayerHero(playerHero: PlayerHero) {
        playerHeroDao.insertPlayerHero(
            LocalPlayerHero(
                playerHero.heroId,
                playerHero.lastPlayed,
                playerHero.games,
                playerHero.win,
                playerHero.withGames,
                playerHero.withWin,
            )
        )
    }

    override fun deletePlayerHeroes() {
        playerHeroDao.deletePlayerHeroes()
    }

    override fun getPlayerHeroes(): List<PlayerHero> {
        val playerHeroes = playerHeroDao.getPlayerHeroes()

        fun LocalPlayerHero.toDomain(): PlayerHero {
            return PlayerHero(
                this.heroId,
                this.lastPlayed,
                this.games,
                this.win,
                this.withGames,
                this.withWin,
            )
        }

        return playerHeroes.map {
            it.toDomain()
        }
    }

    override fun countPlayerHeroes(): Int {
        return playerHeroDao.countPlayerHeroes()
    }

    override fun getPlayerHeroWhere(id: Int): PlayerHero {
        val playerHero = playerHeroDao.getPlayerHeroWhere(id)

        return PlayerHero(
            playerHero.heroId,
            playerHero.lastPlayed,
            playerHero.games,
            playerHero.win,
            playerHero.withGames,
            playerHero.withWin,
        )
    }
}