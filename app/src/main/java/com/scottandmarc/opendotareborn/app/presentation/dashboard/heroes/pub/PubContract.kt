package com.scottandmarc.opendotareborn.app.presentation.dashboard.heroes.pub

import com.scottandmarc.opendotareborn.app.domain.entities.HeroStats
import com.scottandmarc.opendotareborn.toolbox.mvp.BasePresenter
import com.scottandmarc.opendotareborn.toolbox.mvp.BaseView

interface PubContract {
    interface View : BaseView {
        fun updateRv(sortedHeroesStats: List<HeroStats>)
        fun updateCategory(category: String)
    }

    interface Presenter : BasePresenter<View> {
        fun onHeroHeaderClicked()
        fun onPickHeaderClicked(category: String)
        fun onWinHeaderClicked(category: String)

        fun onAllBtnClicked()
        fun onHeraldBtnClicked()
        fun onGuardianBtnClicked()
        fun onCrusaderBtnClicked()
        fun onArchonBtnClicked()
        fun onLegendBtnClicked()
        fun onAncientBtnClicked()
        fun onDivineBtnClicked()
        fun onImmortalBtnClicked()
    }
}