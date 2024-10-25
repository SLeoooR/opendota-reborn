package com.scottandmarc.opendotareborn.app.presentation.dashboard.profile.overview

import android.util.Log
import com.scottandmarc.opendotareborn.app.data.player.PlayerRepository
import com.scottandmarc.opendotareborn.app.data.recentMatches.RecentMatchRepository
import com.scottandmarc.opendotareborn.app.domain.entities.Player
import com.scottandmarc.opendotareborn.app.domain.entities.ProcessedRecentMatch
import com.scottandmarc.opendotareborn.app.domain.entities.RecentMatch
import com.scottandmarc.opendotareborn.toolbox.helpers.CoroutineScopeProvider
import com.scottandmarc.opendotareborn.toolbox.retrofit.NetworkConnectionChecker
import kotlinx.coroutines.launch
import java.lang.Exception

class OverviewPresenter(
    private val accountId: Int,
    private val coroutineScopeProvider: CoroutineScopeProvider,
    private val playerRepository: PlayerRepository,
    private val recentMatchRepository: RecentMatchRepository,
    private val networkConnectionChecker: NetworkConnectionChecker
) : OverviewContract.Presenter {

    private var view: OverviewContract.View? = null
    private lateinit var player: Player
    private lateinit var recentMatches: List<RecentMatch>
    private var processedRecentMatches: MutableList<ProcessedRecentMatch> = mutableListOf()

    private val stats: List<String> =
        listOf(
            "Kills",
            "Deaths",
            "Assists",
            "Gold Per Min",
            "XP Per Min",
            "Last Hits",
            "Hero Damage",
            "Hero Healing",
            "Tower Damage",
            "Duration"
        )

    private var maxKills = 0
    private var maxDeaths = 0
    private var maxAssists = 0
    private var maxGoldPerMin = 0
    private var maxXpPerMin = 0
    private var maxLastHits = 0
    private var maxHeroDamage = 0
    private var maxHeroHealing = 0
    private var maxTowerDamage = 0
    private var maxDuration = 0

    private var totalKills = 0
    private var totalDeaths = 0
    private var totalAssists = 0
    private var totalGoldPerMin = 0
    private var totalXpPerMin = 0
    private var totalLastHits = 0
    private var totalHeroDamage = 0
    private var totalHeroHealing = 0
    private var totalTowerDamage = 0
    private var totalDuration = 0

    private var heroIdKills = 0
    private var heroIdDeaths = 0
    private var heroIdAssists = 0
    private var heroIdGoldPerMin = 0
    private var heroIdXpPerMin = 0
    private var heroIdLastHits = 0
    private var heroIdHeroDamage = 0
    private var heroIdHeroHealing = 0
    private var heroIdTowerDamage = 0
    private var heroIdDuration = 0

    override fun onViewReady(view: OverviewContract.View) {
        this.view = view
        setup()
    }

    private fun setup() {
        if (accountId == 0) {
            player = playerRepository.getPlayer()
            view?.showProfilePic(player.profile.avatarFull)
            view?.showPlayerName(player.profile.personaName)

            val rankPicURL = if (player.rankTier != null) {
                "https://www.opendota.com/assets/images/dota2/rank_icons/rank_icon_${player.rankTier.toString()[0]}.png"
            } else {
                "https://www.opendota.com/assets/images/dota2/rank_icons/rank_icon_0.png"
            }
            val starsPicURL = "https://www.opendota.com/assets/images/dota2/rank_icons/rank_star_${player.rankTier.toString()[1]}.png"
            view?.showPlayerRankPic(rankPicURL, starsPicURL)
            view?.showPlayerWins(player.winLose.win)
            view?.showPlayerLosses(player.winLose.lose)

            val winRate = if ((player.winLose.win + player.winLose.lose) != 0) {
                player.winLose.win.toFloat() / (player.winLose.win.toFloat() + player.winLose.lose.toFloat())
            } else {
                0F
            }
            view?.showPlayerWinRate(winRate * 100)
        }

        coroutineScopeProvider.provide().launch {
            try {
                view?.showLoadingDialog()

                if (networkConnectionChecker.isNetworkAvailable()) {
                    if (accountId != 0) {
                        player = playerRepository.fetchPlayer(accountId)

                        view?.showProfilePic(player.profile.avatarFull)
                        view?.showPlayerName(player.profile.personaName)

                        val rankPicURL = "https://www.opendota.com/assets/images/dota2/rank_icons/rank_icon_${player.rankTier.toString()[0]}.png"
                        val starsPicURL = "https://www.opendota.com/assets/images/dota2/rank_icons/rank_star_${player.rankTier.toString()[1]}.png"
                        view?.showPlayerRankPic(rankPicURL, starsPicURL)
                        view?.showPlayerWins(player.winLose.win)
                        view?.showPlayerLosses(player.winLose.lose)

                        val winRate = player.winLose.win.toFloat() / (player.winLose.win.toFloat() + player.winLose.lose.toFloat())
                        view?.showPlayerWinRate(winRate * 100)
                    }

                    recentMatches = if (accountId == 0) {
                        recentMatchRepository.fetchRecentMatches(player.profile.accountId)
                    } else {
                        recentMatchRepository.fetchRecentMatches(accountId)
                    }

                    recentMatches.forEach {
                        if (it.kills > maxKills) {
                            maxKills = it.kills
                            heroIdKills = it.heroId
                        }
                        if (it.deaths > maxDeaths) {
                            maxDeaths = it.deaths
                            heroIdDeaths = it.heroId
                        }
                        if (it.assists > maxAssists) {
                            maxAssists = it.assists
                            heroIdAssists = it.heroId
                        }
                        if (it.goldPerMin > maxGoldPerMin) {
                            maxGoldPerMin = it.goldPerMin
                            heroIdGoldPerMin = it.heroId
                        }
                        if (it.xpPerMin > maxXpPerMin) {
                            maxXpPerMin = it.xpPerMin
                            heroIdXpPerMin = it.heroId
                        }
                        if (it.lastHits > maxLastHits) {
                            maxLastHits = it.lastHits
                            heroIdLastHits = it.heroId
                        }
                        if (it.heroDamage > maxHeroDamage) {
                            maxHeroDamage = it.heroDamage
                            heroIdHeroDamage = it.heroId
                        }
                        if (it.heroHealing > maxHeroHealing) {
                            maxHeroHealing = it.heroHealing
                            heroIdHeroHealing = it.heroId
                        }
                        if (it.towerDamage > maxTowerDamage) {
                            maxTowerDamage = it.towerDamage
                            heroIdTowerDamage = it.heroId
                        }
                        if (it.duration > maxDuration) {
                            maxDuration = it.duration
                            heroIdDuration = it.heroId
                        }

                        totalKills += it.kills
                        totalDeaths += it.deaths
                        totalAssists += it.assists
                        totalGoldPerMin += it.goldPerMin
                        totalXpPerMin += it.xpPerMin
                        totalLastHits += it.lastHits
                        totalHeroDamage += it.heroDamage
                        totalHeroHealing += it.heroHealing
                        totalTowerDamage += it.towerDamage
                        totalDuration += it.duration
                    }

                    val maximums: List<Int> =
                        listOf(
                            maxKills,
                            maxDeaths,
                            maxAssists,
                            maxGoldPerMin,
                            maxXpPerMin,
                            maxLastHits,
                            maxHeroDamage,
                            maxHeroHealing,
                            maxTowerDamage,
                            maxDuration,
                        )

                    val averages: List<Int> =
                        listOf(
                            totalKills / 20,
                            totalDeaths / 20,
                            totalAssists / 20,
                            totalGoldPerMin / 20,
                            totalXpPerMin / 20,
                            totalLastHits / 20,
                            totalHeroDamage / 20,
                            totalHeroHealing / 20,
                            totalTowerDamage / 20,
                            totalDuration / 20
                        )

                    val heroIDs: List<Int> =
                        listOf(
                            heroIdKills,
                            heroIdDeaths,
                            heroIdAssists,
                            heroIdGoldPerMin,
                            heroIdXpPerMin,
                            heroIdLastHits,
                            heroIdHeroDamage,
                            heroIdHeroHealing,
                            heroIdTowerDamage,
                            heroIdDuration
                        )

                    for (i in 0..9) {
                        processedRecentMatches.add(
                            ProcessedRecentMatch(
                                stats[i],
                                averages[i],
                                maximums[i],
                                heroIDs[i]
                            )
                        )
                    }

                    view?.setProcessedRecentMatches(processedRecentMatches)
                    view?.updateRv()
                }

                view?.dismissLoadingDialog()
            } catch (e: Exception) {
                Log.d("error", e.localizedMessage?: "")
                view?.showOkayDialog(
                    title = "Error",
                    message = e.localizedMessage ?: "",
                    buttonText = "Okay"
                )
            }
        }
    }

    override fun onViewDetach() {
        view = null
    }
}