package com.scottandmarc.opendotareborn.di

import android.content.Context
import com.scottandmarc.opendotareborn.app.data.OpenDotaRebornDatabase
import com.scottandmarc.opendotareborn.app.data.hero.info.HeroInfoRepository
import com.scottandmarc.opendotareborn.app.data.hero.info.createHeroInfoService
import com.scottandmarc.opendotareborn.app.data.hero.player.PlayerHeroRepository
import com.scottandmarc.opendotareborn.app.data.hero.player.createPlayerHeroService
import com.scottandmarc.opendotareborn.app.data.matches.MatchRepository
import com.scottandmarc.opendotareborn.app.data.matches.createMatchService
import com.scottandmarc.opendotareborn.app.data.peers.PeerRepository
import com.scottandmarc.opendotareborn.app.data.peers.createPeerService
import com.scottandmarc.opendotareborn.app.data.player.PlayerRepository
import com.scottandmarc.opendotareborn.app.data.player.createPlayerService
import com.scottandmarc.opendotareborn.app.data.recentMatches.RecentMatchRepository
import com.scottandmarc.opendotareborn.app.data.recentMatches.createRecentMatchService
import com.scottandmarc.opendotareborn.app.data.search.SearchRepository
import com.scottandmarc.opendotareborn.app.data.search.createSearchService
import com.scottandmarc.opendotareborn.app.data.teams.TeamRepository
import com.scottandmarc.opendotareborn.app.data.teams.createTeamService
import com.scottandmarc.opendotareborn.app.data.total.TotalRepository
import com.scottandmarc.opendotareborn.app.data.total.createTotalService
import com.scottandmarc.opendotareborn.app.domain.gateways.RecentMatchGateway
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

    fun providePlayerHeroRepository() : PlayerHeroRepository {
        return PlayerHeroRepository(
            createPlayerHeroService(),
        )
    }

    fun provideMatchRepository() : MatchRepository {
        return MatchRepository(
            createMatchService()
        )
    }

    fun provideRecentMatchRepository() : RecentMatchRepository {
        return RecentMatchRepository(
            createRecentMatchService()
        )
    }

    fun providePeerRepository() : PeerRepository {
        return PeerRepository(
            createPeerService()
        )
    }

    fun provideTotalRepository() : TotalRepository {
        return TotalRepository(
            createTotalService()
        )
    }

    fun provideTeamRepository() : TeamRepository {
        return TeamRepository(
            createTeamService()
        )
    }

    fun provideSearchRepository() : SearchRepository {
        return SearchRepository(
            createSearchService()
        )
    }
}