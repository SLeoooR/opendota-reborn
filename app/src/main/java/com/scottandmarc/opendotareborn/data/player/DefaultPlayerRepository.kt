package com.scottandmarc.opendotareborn.data.player

import android.util.Log
import com.scottandmarc.opendotareborn.data.ResponseCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DefaultPlayerRepository(
    private val playerDao: PlayerDao,
    private val service: PlayerEndpoints
) : PlayerRepository {
    override fun fetchPlayer(accountId: Int, callback: ResponseCallback<RemotePlayer>) {
        service.fetch(accountId).enqueue(object : Callback<RemotePlayer> {
            override fun onFailure(call: Call<RemotePlayer>, t: Throwable) {
                callback.onFailure("${t::class.simpleName}")
            }

            override fun onResponse(call: Call<RemotePlayer>, response: Response<RemotePlayer>) {
                if (response.body() != null) {
                    Log.d("response.body", response.body().toString())
                    callback.onSuccess(response.body()!!)
                } else {
                    callback.onFailure(response.message())
                }
            }
        })
    }

    override fun insert(player: Player) {
        playerDao.insert(player)
    }

    override fun delete() {
        playerDao.delete()
    }

    override fun getPlayer(): Player {
        return playerDao.getPlayer()
    }

    override fun count(): Int {
        return playerDao.count()
    }
}