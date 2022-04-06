package com.scottandmarc.opendotareborn.app.domain.gateways

import com.scottandmarc.opendotareborn.app.data.hero.info.LocalHeroInfo
import com.scottandmarc.opendotareborn.app.domain.entities.Match

interface MatchGateway {
    // API Methods
    suspend fun fetchMatches(accountId: Int): List<Match>

    // Room Methods
    fun insertMatch(match: Match)
    fun deleteMatches()
    fun getMatches(): List<Match>
    fun countMatches(): Int
    fun getMatch(id: Int): Match
}