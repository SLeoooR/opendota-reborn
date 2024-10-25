package com.scottandmarc.opendotareborn.app.presentation.dashboard.profile.matches.matchDetails

import com.scottandmarc.opendotareborn.app.domain.entities.MatchPlayer
import com.scottandmarc.opendotareborn.toolbox.mvp.BasePresenter
import com.scottandmarc.opendotareborn.toolbox.mvp.BaseView

interface MatchDetailsContract {
    interface View : BaseView {
        fun showLoadingDialog()
        fun dismissLoadingDialog()

        fun showMatchId(matchId: String)
        fun showVictory(isRadiant: Boolean)
        fun showTeamKills(radiant: Int, dire: Int)
        fun showGeneralDetails(
            gameMode: String,
            duration: String,
            region: String,
            lastPlayed: String,
        )

        fun updateRv()
        fun setRadiantPlayersDetails(radiantPlayers: List<MatchPlayer>)
        fun setDirePlayersDetails(direPlayers: List<MatchPlayer>)

        fun showOkayDialog(title: String, message: String, buttonText: String)
    }

    interface Presenter : BasePresenter<View> {
    }
}