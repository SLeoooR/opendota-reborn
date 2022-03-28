package com.scottandmarc.opendotareborn.app.presentation.profile.overview

import com.scottandmarc.opendotareborn.app.data.player.PlayerRepository
import com.scottandmarc.opendotareborn.app.domain.entities.Player
import com.scottandmarc.opendotareborn.app.domain.entities.WinLose

class OverviewPresenter(
    private val playerRepository: PlayerRepository
) : OverviewContract.Presenter {

    private var view: OverviewContract.View? = null
    private lateinit var player: Player
    private lateinit var winLose: WinLose

    override fun onViewReady(view: OverviewContract.View) {
        this.view = view
        player = playerRepository.getPlayer()
        winLose = playerRepository.getWinLose()
        setup()
    }

    private fun setup() {
        view?.showProfilePic(player.profile?.avatarFull!!)
        view?.showPlayerName(player.profile?.personaName!!)
        view?.showPlayerRankPic(
            rankPicURL = "https://www.opendota.com/assets/images/dota2/rank_icons/rank_icon_${player.rankTier.toString()[0]}.png",
            starsPicURL = "https://www.opendota.com/assets/images/dota2/rank_icons/rank_star_${player.rankTier.toString()[1]}.png"
        )
        view?.showPlayerWins(winLose.win)
        view?.showPlayerLosses(winLose.lose)
        val winRate = winLose.win.toFloat() / (winLose.win.toFloat() + winLose.lose.toFloat())
        view?.showPlayerWinRate(winRate * 100)
    }

    override fun onViewDetach() {
        view = null
    }
}