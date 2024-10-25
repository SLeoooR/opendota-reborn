package com.scottandmarc.opendotareborn.app.data.recentMatches

import android.util.Log
import com.scottandmarc.opendotareborn.app.domain.entities.RecentMatch
import com.scottandmarc.opendotareborn.app.domain.gateways.RecentMatchGateway

class RecentMatchRepository(
    private val recentMatchService: RecentMatchEndpoints
) : RecentMatchGateway {
    override suspend fun fetchRecentMatches(accountId: Int): List<RecentMatch> {
        return try {
            val fetchedRecentMatches = recentMatchService.fetchRecentMatches(accountId).body()!!

            fun RemoteRecentMatch.toDomain(): RecentMatch {
                return RecentMatch(
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
                    this.xpPerMin,
                    this.goldPerMin,
                    this.heroDamage,
                    this.towerDamage,
                    this.heroHealing,
                    this.lastHits,
                    this.lane,
                    this.laneRole,
                    this.isRoaming,
                    this.cluster,
                    this.leaverStatus,
                    this.partySize,
                )
            }

            fetchedRecentMatches.map {
                it.toDomain()
            }
        } catch (e: Exception) {
            Log.d("error", e.localizedMessage?: "")
            throw e
        }
    }
}