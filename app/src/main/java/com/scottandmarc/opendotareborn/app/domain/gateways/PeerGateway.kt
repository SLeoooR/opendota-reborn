package com.scottandmarc.opendotareborn.app.domain.gateways

import com.scottandmarc.opendotareborn.app.domain.entities.Peer

interface PeerGateway {
    // API Method
    suspend fun fetchPeers(
        accountId: Int
    ): List<Peer>
}