package com.scottandmarc.opendotareborn.app.domain.gateways

import com.scottandmarc.opendotareborn.app.domain.entities.Search

interface SearchGateway {
    // API Method
    suspend fun fetchSearches(
        query: String
    ): List<Search>
}