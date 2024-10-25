package com.scottandmarc.opendotareborn.app.presentation.dashboard.profile.overview

import com.scottandmarc.opendotareborn.app.domain.entities.ProcessedRecentMatch
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

        fun showLoadingDialog()
        fun dismissLoadingDialog()

        fun setProcessedRecentMatches(processedRecentMatches: MutableList<ProcessedRecentMatch>)
        fun updateRv()
        fun showOkayDialog(title: String, message: String, buttonText: String)
    }

    interface Presenter : BasePresenter<View> {

    }
}