package com.scottandmarc.opendotareborn.app.domain.gateways

import com.scottandmarc.opendotareborn.app.domain.entities.RecentMatch

interface RecentMatchGateway {
    // API Method
    suspend fun fetchRecentMatches(
        accountId: Int
    ): List<RecentMatch>
}