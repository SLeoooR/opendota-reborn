package com.scottandmarc.opendotareborn.app.domain.gateways

import com.scottandmarc.opendotareborn.app.domain.entities.Team

interface TeamGateway {
    // API Method
    suspend fun fetchTeams(): List<Team>
}