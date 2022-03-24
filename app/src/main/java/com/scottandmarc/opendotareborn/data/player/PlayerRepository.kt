package com.scottandmarc.opendotareborn.data.player

import com.scottandmarc.opendotareborn.data.ResponseCallback

interface PlayerRepository {
    // API Method
    fun fetchPlayer(accountId: Int, callback: ResponseCallback<RemotePlayer>)

    // Room Methods
    fun insert(player: Player)
    fun delete()
    fun getPlayer(): Player
    fun count(): Int
}