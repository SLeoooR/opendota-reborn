package com.scottandmarc.opendotareborn.app.presentation.profile.overview

import com.scottandmarc.opendotareborn.toolbox.mvp.BasePresenter
import com.scottandmarc.opendotareborn.toolbox.mvp.BaseView

interface OverviewContract {
    interface View : BaseView {
        fun showProfilePic(profilePicURL: String)

        fun showPlayerRankPic(rankPicURL: String, starsPicURL: String)
        fun showPlayerName(playerName: String)

        fun showPlayerWins(wins: Int)
        fun showPlayerLosses(losses: Int)
        fun showPlayerWinRate(winRate: Float)
    }

    interface Presenter : BasePresenter<View> {

    }
}