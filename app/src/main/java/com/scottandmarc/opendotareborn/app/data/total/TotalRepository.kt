package com.scottandmarc.opendotareborn.app.data.total

import android.util.Log
import com.scottandmarc.opendotareborn.app.domain.entities.Total
import com.scottandmarc.opendotareborn.app.domain.gateways.TotalGateway
import java.lang.Exception

class TotalRepository(
    private val totalEndpoints: TotalEndpoints
) : TotalGateway {
    override suspend fun fetchTotals(accountId: Int): List<Total> {
        return try {
            val fetchedTotals = totalEndpoints.fetchTotals(accountId).body()!!

            fun RemoteTotal.toDomain(): Total {
                return Total(
                    this.field,
                    this.n,
                    this.sum
                )
            }

            fetchedTotals.map {
                it.toDomain()
            }
        } catch (e: Exception) {
            Log.d("error", e.localizedMessage?: "")
            throw e
        }
    }
}