package com.scottandmarc.opendotareborn.app.domain.gateways

import com.scottandmarc.opendotareborn.app.domain.entities.MatchDetails

interface MatchDetailsGateway {
    // API Methods
    suspend fun fetchMatchDetails(
        matchId: Long,
    ): MatchDetails
}