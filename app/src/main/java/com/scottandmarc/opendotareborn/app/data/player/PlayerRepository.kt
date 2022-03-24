package com.scottandmarc.opendotareborn.app.data.player

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.scottandmarc.opendotareborn.app.data.player.PlayerMapper.toDomain
import com.scottandmarc.opendotareborn.app.data.player.PlayerMapper.toLocal
import com.scottandmarc.opendotareborn.app.domain.entities.Player
import com.scottandmarc.opendotareborn.toolbox.helpers.ResponseCallback
import com.scottandmarc.opendotareborn.app.domain.gateways.PlayerGateway
import okhttp3.internal.wait
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayerRepository(
    private val playerDao: PlayerDao,
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
}