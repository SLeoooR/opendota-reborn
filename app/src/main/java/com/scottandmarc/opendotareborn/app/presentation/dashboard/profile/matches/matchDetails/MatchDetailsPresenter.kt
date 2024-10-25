package com.scottandmarc.opendotareborn.app.presentation.dashboard.profile.matches.matchDetails

import android.util.Log
import com.scottandmarc.opendotareborn.app.data.matchDetails.MatchDetailsRepository
import com.scottandmarc.opendotareborn.app.domain.entities.MatchDetails
import com.scottandmarc.opendotareborn.app.domain.entities.MatchPlayer
import com.scottandmarc.opendotareborn.toolbox.helpers.CoroutineScopeProvider
import com.scottandmarc.opendotareborn.toolbox.helpers.TimeHelper
import com.scottandmarc.opendotareborn.toolbox.retrofit.NetworkConnectionChecker
import kotlinx.coroutines.launch

class MatchDetailsPresenter(
    private val matchId: Long,
    private val matchDetailsRepository: MatchDetailsRepository,
    private val coroutineScopeProvider: CoroutineScopeProvider,
    private val networkConnectionChecker: NetworkConnectionChecker
) : MatchDetailsContract.Presenter {
    private var view: MatchDetailsContract.View? = null

    private lateinit var matchDetails: MatchDetails
    private var radiantPlayers = mutableListOf<MatchPlayer>()
    private var direPlayers = mutableListOf<MatchPlayer>()

    private fun setup() {
        coroutineScopeProvider.provide().launch {
            try {
                view?.showLoadingDialog()

                if (networkConnectionChecker.isNetworkAvailable()) {
                    matchDetails = matchDetailsRepository.fetchMatchDetails(matchId)

                    matchDetails.players?.forEach { matchPlayer ->
                        if (matchPlayer.playerSlot in 0..127) {
                            radiantPlayers.add(matchPlayer)
                        } else {
                            direPlayers.add(matchPlayer)
                        }
                    }

                    view?.setRadiantPlayersDetails(radiantPlayers)
                    view?.setDirePlayersDetails(direPlayers)

                    view?.showMatchId(matchDetails.matchId.toString())
                    matchDetails.radiantWin?.let { view?.showVictory(it) }
                    matchDetails.radiantScore?.let { radiantScore ->
                        matchDetails.direScore?.let { direScore ->
                            view?.showTeamKills(radiantScore, direScore)
                        }
                    }

                    val matchDurationInSeconds = matchDetails.duration
                    val minutes = (matchDurationInSeconds?.rem(3600))?.div(60)
                    val seconds = matchDurationInSeconds?.rem(60)

                    val duration: String = if (minutes!! < 10) {
                        if (seconds!! < 10) {
                            "0$minutes:0$seconds"
                        } else {
                            "0$minutes:$seconds"
                        }
                    } else {
                        if (seconds!! < 10) {
                            "$minutes:0$seconds"
                        } else {
                            "$minutes:$seconds"
                        }
                    }

                    regionMap[matchDetails.region]?.let { region ->
                        gameModeMap[matchDetails.gameMode]?.let { gameMode ->
                            view?.showGeneralDetails(
                                gameMode,
                                duration,
                                region,
                                TimeHelper.numTimeAgo((matchDetails.startTime!! + matchDurationInSeconds)) + " ago"
                            )
                        }
                    }

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

    override fun onViewReady(view: MatchDetailsContract.View) {
        this.view = view
        setup()
    }

    override fun onViewDetach() {
        view = null
    }
}

enum class GameMode{

}

val gameModeMap: Map<Int, String> = mapOf(
    0 to "Unknown",
    1 to "All Pick",
    2 to "Captains Mode",
    3 to "Random Draft",
    4 to "Single Draft",
    5 to "All Random",
    6 to "Intro",
    7 to "Diretide",
    8 to "Reverse Captains Mode",
    9 to "Greeviling",
    10 to "Tutorial",
    11 to "Mid Only",
    12 to "Least Played",
    13 to "Limited Heroes",
    14 to "Compendium Matchmaking",
    15 to "Custom",
    16 to "Captains Draft",
    17 to "Balanced Draft",
    18 to "Ability Draft",
    19 to "Event",
    20 to "All Random Death Match",
    21 to "1V1 Mid",
    22 to "All Draft",
    23 to "Turbo",
    24 to "Mutation",
    25 to "Coaches Challenge"
)

val regionMap: Map<Int, String> = mapOf(
     1 to "US West",
     2 to "US East",
     3 to "Europe",
     5 to "Singapore",
     6 to "Dubai",
     7 to "Australia",
     8 to "Stockholm",
     9 to "Austria",
    10 to "Brazil",
    11 to "South Africa",
    12 to "Shanghai",
    13 to "UNICOM",
    14 to "Chile",
    15 to "Peru",
    16 to "India",
    17 to "Guangdong",
    18 to "Zhejiang",
    19 to "Japan",
    20 to "Wuhan",
    25 to "Tianjin",
    37 to "Taiwan",
    38 to "Argentina"
)