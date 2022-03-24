package com.scottandmarc.opendotareborn

import android.content.Context
import com.scottandmarc.opendotareborn.data.OpenDotaRebornDatabase
import com.scottandmarc.opendotareborn.data.player.DefaultPlayerRepository
import com.scottandmarc.opendotareborn.data.player.PlayerRepository
import com.scottandmarc.opendotareborn.data.player.createPlayerService

object DependencyInjector {
    fun providePlayerRepository(
        appContext: Context
    ): PlayerRepository {

        val db = OpenDotaRebornDatabase.getDatabase(appContext)

        return DefaultPlayerRepository(
            db.getPlayerDao(),
            createPlayerService()
        )
    }
}