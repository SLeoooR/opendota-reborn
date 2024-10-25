package com.scottandmarc.opendotareborn.app.data.matches

import android.util.Log
import com.scottandmarc.opendotareborn.app.domain.entities.Match
import com.scottandmarc.opendotareborn.app.domain.gateways.MatchGateway

class MatchRepository(
    private val matchService: MatchEndpoints
) : MatchGateway {
    override suspend fun fetchMatches(accountId: Int): List<Match> {
        return try {
            val fetchedMatches = matchService.fetchMatches(accountId).body()!!

            fun RemoteMatch.toDomain(): Match {
                return Match(
                    this.matchId,
                    this.playerSlot,
                    this.radiantWin,
                    this.duration,
                    this.gameMode,
                    this.lobbyType,
                    this.heroId,
                    this.startTime,
                    this.version,
                    this.kills,
                    this.deaths,
                    this.assists,
                    this.skill,
                    this.leaverStatus,
                    this.partySize
                )
            }

            fetchedMatches.map {
                it.toDomain()
            }
        } catch (e: Exception) {
            Log.d("error", e.localizedMessage?: "")
            throw e
        }
    }
}