package com.scottandmarc.opendotareborn.app.data.player

import android.util.Log
import com.scottandmarc.opendotareborn.app.data.player.PlayerMapper.toDomain
import com.scottandmarc.opendotareborn.app.data.player.PlayerMapper.toLocal
import com.scottandmarc.opendotareborn.app.data.player.WinLoseMapper.toDomain
import com.scottandmarc.opendotareborn.app.data.player.WinLoseMapper.toLocal
import com.scottandmarc.opendotareborn.app.domain.entities.Player
import com.scottandmarc.opendotareborn.app.domain.entities.WinLose
import com.scottandmarc.opendotareborn.toolbox.helpers.ResponseCallback
import com.scottandmarc.opendotareborn.app.domain.gateways.PlayerGateway
import com.scottandmarc.opendotareborn.app.domain.gateways.WinLoseGateway
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayerRepository(
    private val playerDao: PlayerDao,
    private val winLoseDao: WinLoseDao,
    private val service: PlayerEndpoints
) : PlayerGateway {
    override fun fetchPlayer(accountId: Int, callback: ResponseCallback<Player>) {
        service.fetch(accountId).enqueue(object : Callback<RemotePlayer> {
            override fun onFailure(call: Call<RemotePlayer>, t: Throwable) {
                callback.onFailure("${t::class.simpleName}")
            }

            override fun onResponse(call: Call<RemotePlayer>, response: Response<RemotePlayer>) {
                if (response.body() != null) {
                    Log.d("response.body", response.body().toString())
                    callback.onSuccess(response.body()!!.toDomain())
                } else {
                    callback.onFailure(response.message())
                }
            }
        })
    }

    override fun fetchWinLose(accountId: Int, callback: ResponseCallback<WinLose>) {
        service.fetchWinLose(accountId).enqueue(object : Callback<RemoteWinLose> {
            override fun onFailure(call: Call<RemoteWinLose>, t: Throwable) {
                callback.onFailure("${t::class.simpleName}")
            }

            override fun onResponse(call: Call<RemoteWinLose>, response: Response<RemoteWinLose>) {
                if (response.body() != null) {
                    val remoteWinLose: RemoteWinLose = response.body()!!
                    remoteWinLose.id = accountId

                    callback.onSuccess(remoteWinLose.toDomain())
                } else {
                    callback.onFailure(response.message())
                }
            }
        })
    }

    // Player DAO
    override fun insert(player: Player) {
        playerDao.insert(player.toLocal())
    }

    override fun delete() {
        playerDao.delete()
    }

    override fun getPlayer(): Player {
        return playerDao.getPlayer().toDomain()
    }

    override fun count(): Int {
        return playerDao.count()
    }

    // WinLose DAO
    override fun insertWinLose(winLose: WinLose) {
        winLoseDao.insert(winLose.toLocal())
    }

    override fun deleteWinLose() {
        winLoseDao.delete()
    }

    override fun getWinLose(): WinLose {
        return winLoseDao.getWinLose().toDomain()
    }

    override fun countWinLose(): Int {
        return winLoseDao.count()
    }
}