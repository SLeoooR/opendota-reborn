package com.scottandmarc.opendotareborn.app.domain.gateways

import com.scottandmarc.opendotareborn.app.domain.entities.Player

interface PlayerGateway {
    // API Method
    suspend fun fetchPlayer(
        accountId: Int,
    ): Player

    // Room Methods
    fun insert(player: Player)
    fun delete()
    fun getPlayer(): Player
    fun count(): Int
}