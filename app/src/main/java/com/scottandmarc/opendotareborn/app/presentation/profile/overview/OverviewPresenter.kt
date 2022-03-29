package com.scottandmarc.opendotareborn.app.presentation.profile.overview

import android.util.Log
import com.scottandmarc.opendotareborn.app.data.hero.HeroesRepository
import com.scottandmarc.opendotareborn.app.data.hero.RemoteHeroes
import com.scottandmarc.opendotareborn.app.data.player.PlayerRepository
import com.scottandmarc.opendotareborn.app.domain.entities.Player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OverviewPresenter(
    private val playerRepository: PlayerRepository,
    private val heroesRepository: HeroesRepository,
) : OverviewContract.Presenter {

    private var view: OverviewContract.View? = null
    private lateinit var player: Player
    private lateinit var heroes: List<RemoteHeroes.RemoteHero>
    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onViewReady(view: OverviewContract.View) {
        this.view = view
        player = playerRepository.getPlayer()

        scope.launch {
            try {
                heroes = heroesRepository.fetchHeroes(player.profile?.accountId)
                Log.d("heroes0", heroes[0].toString())
                Log.d("heroes1", heroes[1].toString())
                Log.d("heroes2", heroes[2].toString())
            } catch(t: Throwable) {
                t.printStackTrace()
            }
        }

        setup()
    }

    private fun setup() {
        view?.showProfilePic(player.profile?.avatarFull!!)
        view?.showPlayerName(player.profile?.personaName!!)
        view?.showPlayerRankPic(
            rankPicURL = "https://www.opendota.com/assets/images/dota2/rank_icons/rank_icon_${player.rankTier.toString()[0]}.png",
            starsPicURL = "https://www.opendota.com/assets/images/dota2/rank_icons/rank_star_${player.rankTier.toString()[1]}.png"
        )
        view?.showPlayerWins(player.winLose.win)
        view?.showPlayerLosses(player.winLose.lose)
        val winRate = player.winLose.win.toFloat() / (player.winLose.win.toFloat() + player.winLose.lose.toFloat())
        view?.showPlayerWinRate(winRate * 100)
    }

    override fun onViewDetach() {
        view = null
    }
}