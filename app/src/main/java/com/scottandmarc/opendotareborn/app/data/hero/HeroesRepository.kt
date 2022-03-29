package com.scottandmarc.opendotareborn.app.data.hero

import android.util.Log
import com.scottandmarc.opendotareborn.app.domain.gateways.HeroesGateway

class HeroesRepository(
    private val heroesService: HeroesEndpoints
) : HeroesGateway {
    override suspend fun fetchHeroes(accountId: Int?): List<RemoteHeroes.RemoteHero> {
        return try {
            heroesService.fetchHeroes(accountId).body()!!
        } catch (e: Exception) {
            Log.d("error", e.localizedMessage?: "")
            throw e
        }
    }
}