package com.scottandmarc.opendotareborn.app.presentation.dashboard.heroes.pro

import com.scottandmarc.opendotareborn.app.domain.entities.HeroStats
import com.scottandmarc.opendotareborn.toolbox.mvp.BasePresenter
import com.scottandmarc.opendotareborn.toolbox.mvp.BaseView

interface ProContract {
    interface View : BaseView {
        fun updateRv(sortedHeroesStats: List<HeroStats>)
    }

    interface Presenter : BasePresenter<View> {
        fun onHeroHeaderClicked()
        fun onPickHeaderClicked()
        fun onBanHeaderClicked()
        fun onWinHeaderClicked()
    }
}