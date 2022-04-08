package com.scottandmarc.opendotareborn.app.presentation.profile.overview

import com.scottandmarc.opendotareborn.app.data.player.PlayerRepository
import com.scottandmarc.opendotareborn.app.domain.entities.Player

class OverviewPresenter(
    private val playerRepository: PlayerRepository,
) : OverviewContract.Presenter {

    private var view: OverviewContract.View? = null
    private lateinit var player: Player

    override fun onViewReady(view: OverviewContract.View) {
        this.view = view
        setup()
    }

    private fun setup() {
        player = playerRepository.getPlayer()
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