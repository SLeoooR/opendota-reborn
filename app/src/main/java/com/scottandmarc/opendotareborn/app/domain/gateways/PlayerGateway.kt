package com.scottandmarc.opendotareborn.app.domain.gateways

import com.scottandmarc.opendotareborn.toolbox.helpers.ResponseCallback
import com.scottandmarc.opendotareborn.app.data.player.RemotePlayer
import com.scottandmarc.opendotareborn.app.domain.entities.Player

interface PlayerGateway {
    // API Method
    fun fetchPlayer(
        accountId: Int,
        callback: ResponseCallback<Player>
    )

    // Room Methods
    fun insert(player: Player)
    fun delete()
    fun getPlayer(): Player
    fun count(): Int
}