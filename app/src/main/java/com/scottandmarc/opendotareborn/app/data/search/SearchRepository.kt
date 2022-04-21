package com.scottandmarc.opendotareborn.app.data.search

import android.util.Log
import com.scottandmarc.opendotareborn.app.domain.entities.Search
import com.scottandmarc.opendotareborn.app.domain.gateways.SearchGateway

class SearchRepository(
    private val searchService: SearchEndpoints
) : SearchGateway {
    override suspend fun fetchSearches(query: String): List<Search> {
        return try {
            val fetchedSearches = searchService.fetchSearches(query).body()!!

            fun RemoteSearch.toDomain(): Search {
                return Search(
                    this.accountId,
                    this.personaname,
                    this.avatarfull,
                    this.lastMatchTime?: "",
                    this.similarity
                )
            }

            fetchedSearches.map {
                it.toDomain()
            }
        } catch (e: Exception) {
            Log.d("error", e.localizedMessage?: "")
            throw e
        }
    }
}