package com.scottandmarc.opendotareborn.di

import android.content.Context
import com.scottandmarc.opendotareborn.app.data.OpenDotaRebornDatabase
import com.scottandmarc.opendotareborn.app.data.hero.info.HeroInfoRepository
import com.scottandmarc.opendotareborn.app.data.hero.info.createHeroInfoService
import com.scottandmarc.opendotareborn.app.data.hero.player.PlayerHeroRepository
import com.scottandmarc.opendotareborn.app.data.hero.player.createPlayerHeroService
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

    fun provideHeroInfoRepository(
        appContext: Context
    ) : HeroInfoRepository {
        val db = OpenDotaRebornDatabase.getDatabase(appContext)

        return HeroInfoRepository(
            db.getHeroInfoDao(),
            createHeroInfoService()
        )
    }

    fun providePlayerHeroRepository(
        appContext: Context
    ) : PlayerHeroRepository {
        val db = OpenDotaRebornDatabase.getDatabase(appContext)

        return PlayerHeroRepository(
            db.getPlayerHeroDao(),
            createPlayerHeroService(),
        )
    }
}