package com.scottandmarc.opendotareborn.app.presentation.profile.overview

import com.scottandmarc.opendotareborn.app.data.hero.info.HeroInfoRepository
import com.scottandmarc.opendotareborn.app.data.hero.player.PlayerHeroRepository
import com.scottandmarc.opendotareborn.app.data.player.PlayerRepository
import com.scottandmarc.opendotareborn.app.domain.entities.Player
import com.scottandmarc.opendotareborn.app.domain.entities.PlayerHero
import com.scottandmarc.opendotareborn.toolbox.helpers.CoroutineScopeProvider
import kotlinx.coroutines.launch

class OverviewPresenter(
    private val coroutineScopeProvider: CoroutineScopeProvider,
    private val playerRepository: PlayerRepository,
    private val playerHeroesRepository: PlayerHeroRepository,
    private val heroInfoRepository: HeroInfoRepository
) : OverviewContract.Presenter {

    private var view: OverviewContract.View? = null
    private lateinit var player: Player
    private lateinit var playerHeroes: List<PlayerHero>

    override fun onViewReady(view: OverviewContract.View) {
        this.view = view
        player = playerRepository.getPlayer()

        coroutineScopeProvider.provide().launch {
            try {
                playerHeroes = playerHeroesRepository.fetchHeroes(player.profile.accountId)
                view.initRv(heroInfoRepository, playerHeroes)
            } catch(t: Throwable) {
                t.printStackTrace()
            }
        }
        setup()
    }

    private fun setup() {
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

    override fun onViewDetach() {
        view = null
    }
}