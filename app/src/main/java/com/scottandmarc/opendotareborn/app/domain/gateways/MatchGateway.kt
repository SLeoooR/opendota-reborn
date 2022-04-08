package com.scottandmarc.opendotareborn.app.domain.gateways

import com.scottandmarc.opendotareborn.app.domain.entities.Match

interface MatchGateway {
    // API Methods
    suspend fun fetchMatches(
        accountId: Int,
    ): List<Match>
}