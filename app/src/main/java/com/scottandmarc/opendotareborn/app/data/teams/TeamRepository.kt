package com.scottandmarc.opendotareborn.app.data.teams

import android.util.Log
import com.scottandmarc.opendotareborn.app.domain.entities.Team
import com.scottandmarc.opendotareborn.app.domain.gateways.TeamGateway
import java.lang.Exception

class TeamRepository(
    private val teamService: TeamEndpoints
) : TeamGateway {
    override suspend fun fetchTeams(): List<Team> {
        return try {
            val fetchedTeams = teamService.fetchTeams().body()!!

            fun RemoteTeam.toDomain(): Team {
                return Team(
                    this.teamId,
                    this.rating,
                    this.wins,
                    this.losses,
                    this.lastMachTime,
                    this.name,
                    this.tag,
                    this.logoURL
                )
            }

            fetchedTeams.map {
                it.toDomain()
            }
        } catch (e: Exception) {
            Log.d("error", e.localizedMessage?: "")
            throw e
        }
    }
}