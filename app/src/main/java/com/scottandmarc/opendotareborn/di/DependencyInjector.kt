package com.scottandmarc.opendotareborn.di

import android.content.Context
import com.scottandmarc.opendotareborn.app.data.OpenDotaRebornDatabase
import com.scottandmarc.opendotareborn.app.data.hero.HeroesRepository
import com.scottandmarc.opendotareborn.app.data.hero.createHeroesService
import com.scottandmarc.opendotareborn.app.data.player.PlayerRepository
import com.scottandmarc.opendotareborn.app.data.player.createPlayerService
import com.scottandmarc.opendotareborn.toolbox.helpers.CoroutineScopeProvider

object DependencyInjector {
    fun provideCoroutineScopeProvider(): CoroutineScopeProvider {
        return CoroutineScopeProvider()
    }

    fun providePlayerRepository(
        appContext: Context
    ) : PlayerRepository {

        val db = OpenDotaRebornDatabase.getDatabase(appContext)

        return PlayerRepository(
            db.getPlayerDao(),
            createPlayerService(),
        )
    }

    fun provideHeroesRepository() : HeroesRepository {
        return HeroesRepository(
            createHeroesService()
        )
    }
}