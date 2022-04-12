package com.scottandmarc.opendotareborn.app.domain.gateways

import com.scottandmarc.opendotareborn.app.domain.entities.Total

interface TotalGateway {
    // API Method
    suspend fun fetchTotals(
        accountId: Int
    ): List<Total>
}