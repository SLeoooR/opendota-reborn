package com.scottandmarc.opendotareborn.di

import android.content.Context
import com.scottandmarc.opendotareborn.app.data.OpenDotaRebornDatabase
import com.scottandmarc.opendotareborn.app.data.player.PlayerRepository
import com.scottandmarc.opendotareborn.app.data.player.createPlayerService

object DependencyInjector {
    fun providePlayerRepository(
        appContext: Context
    ): PlayerRepository {

        val db = OpenDotaRebornDatabase.getDatabase(appContext)

        return PlayerRepository(
            db.getPlayerDao(),
            db.getWinLoseDao(),
            createPlayerService(),
        )
    }
}