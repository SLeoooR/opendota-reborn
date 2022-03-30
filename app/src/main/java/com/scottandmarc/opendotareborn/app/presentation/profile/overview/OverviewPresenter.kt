package com.scottandmarc.opendotareborn.app.presentation.profile.overview

import android.util.Log
import com.scottandmarc.opendotareborn.app.data.hero.info.HeroInfoRepository
import com.scottandmarc.opendotareborn.app.data.hero.player.PlayerHeroRepository
import com.scottandmarc.opendotareborn.app.data.player.PlayerRepository
import com.scottandmarc.opendotareborn.app.domain.entities.HeroInfo
import com.scottandmarc.opendotareborn.app.domain.entities.Player
import com.scottandmarc.opendotareborn.app.domain.entities.PlayerHero
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Timestamp
import java.text.DateFormat.getDateInstance
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class OverviewPresenter(
    private val playerRepository: PlayerRepository,
    private val playerHeroesRepository: PlayerHeroRepository,
    private val heroInfoRepository: HeroInfoRepository
) : OverviewContract.Presenter {

    private var view: OverviewContract.View? = null
    private lateinit var player: Player
    private lateinit var playerHeroes: List<PlayerHero>
    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onViewReady(view: OverviewContract.View) {
        this.view = view
        player = playerRepository.getPlayer()

        scope.launch {
            try {
                playerHeroes = playerHeroesRepository.fetchHeroes(player.profile.accountId)

                for(hero in playerHeroes) {
                    val heroInfo = heroInfoRepository.getHeroInfoWhere(hero.heroId.toInt())
                    val heroWinRate = if (hero.games != 0) {
                        (hero.win.toFloat() / hero.games.toFloat()) * 100.0F
                    } else {
                        0.0F
                    }

                    val simpleDateFormat = SimpleDateFormat("MM-dd-yyyy HH", Locale.getDefault())
                    val currentDate = simpleDateFormat.format(Timestamp(System.currentTimeMillis()).time)
                    val lastPlayedDate = simpleDateFormat.format(Date(hero.lastPlayed.toLong() * 1000))

                    Log.d(
                        "Hero Info",
                        "https://steamcdn-a.akamaihd.net/apps/dota2/images/dota_react/heroes/${heroInfo.name.substring(14)}.png, " +
                                "${heroInfo.localizedName}, ${hero.games}, " +
                                "${String.format("%.2f", heroWinRate)}%, " +
                                "$currentDate - $lastPlayedDate"
                    )
                }
            } catch(t: Throwable) {
                t.printStackTrace()
            }
        }

        setup()
    }

    private fun setup() {
        view?.showProfilePic(player.profile.avatarFull)
        view?.showPlayerName(player.profile.personaName)
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