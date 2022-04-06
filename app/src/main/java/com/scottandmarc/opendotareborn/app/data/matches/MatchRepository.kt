package com.scottandmarc.opendotareborn.app.data.matches

import android.util.Log
import com.scottandmarc.opendotareborn.app.domain.entities.Match
import com.scottandmarc.opendotareborn.app.domain.gateways.MatchGateway
import java.lang.Exception

class MatchRepository(
    private val matchDao: MatchDao,
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

    override fun insertMatch(match: Match) {
        matchDao.insertMatch(
            LocalMatch(
                match.matchId,
                match.playerSlot,
                match.radiantWin,
                match.duration,
                match.gameMode,
                match.lobbyType,
                match.heroId,
                match.startTime,
                match.version,
                match.kills,
                match.deaths,
                match.assists,
                match.skill,
                match.leaverStatus,
                match.partySize,
            )
        )
    }

    override fun deleteMatches() {
        matchDao.deleteMatches()
    }

    override fun getMatches(): List<Match> {
        val localMatches = matchDao.getMatches()

        fun LocalMatch.toDomain(): Match {
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

        return localMatches.map {
            it.toDomain()
        }
    }

    override fun countMatches(): Int {
        return matchDao.countMatches()
    }

    override fun getMatch(id: Int): Match {
        val localMatch = matchDao.getMatch(id)

        return Match(
            localMatch.matchId,
            localMatch.playerSlot,
            localMatch.radiantWin,
            localMatch.duration,
            localMatch.gameMode,
            localMatch.lobbyType,
            localMatch.heroId,
            localMatch.startTime,
            localMatch.version,
            localMatch.kills,
            localMatch.deaths,
            localMatch.assists,
            localMatch.skill,
            localMatch.leaverStatus,
            localMatch.partySize
        )
    }
}