package com.scottandmarc.opendotareborn.app.data.peers

import android.util.Log
import com.scottandmarc.opendotareborn.app.domain.entities.Peer
import com.scottandmarc.opendotareborn.app.domain.gateways.PeerGateway
import java.lang.Exception

class PeerRepository(
    private val peerEndpoints: PeerEndpoints
) : PeerGateway {
    override suspend fun fetchPeers(accountId: Int): List<Peer> {
        return try {
            val fetchedPeers = peerEndpoints.fetchPeers(accountId).body()!!

            fun RemotePeer.toDomain(): Peer {
                return Peer(
                    this.accountId,
                    this.lastPlayed,
                    this.win,
                    this.games,
                    this.withWin,
                    this.withGames,
                    this.againstWin,
                    this.withGPMSum,
                    this.withGPMSum,
                    this.withXPMSum,
                    this.personaname,
                    this.name,
                    this.isContributor,
                    this.lastLogin,
                    this.avatar,
                    this.avatarfull
                )
            }

            fetchedPeers.map {
                it.toDomain()
            }
        } catch (e: Exception) {
            Log.d("error", e.localizedMessage?: "")
            throw e
        }
    }
}