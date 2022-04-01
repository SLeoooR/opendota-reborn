package com.scottandmarc.opendotareborn.app.presentation.profile.overview

import android.content.Context
import com.scottandmarc.opendotareborn.app.data.hero.info.HeroInfoRepository
import com.scottandmarc.opendotareborn.app.domain.entities.PlayerHero
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

        fun initRv(heroInfoRepository: HeroInfoRepository, playerHeroList: List<PlayerHero>)
    }

    interface Presenter : BasePresenter<View> {

    }
}