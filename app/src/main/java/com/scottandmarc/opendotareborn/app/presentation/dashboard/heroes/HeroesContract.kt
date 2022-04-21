package com.scottandmarc.opendotareborn.app.presentation.dashboard.heroes

import com.scottandmarc.opendotareborn.app.domain.entities.HeroStats
import com.scottandmarc.opendotareborn.toolbox.mvp.BasePresenter
import com.scottandmarc.opendotareborn.toolbox.mvp.BaseView

interface HeroesContract {
    interface View : BaseView {
        fun setHeroesStats(heroesStats: List<HeroStats>)

        fun showLoadingDialog()
        fun dismissLoadingDialog()
    }

    interface Presenter : BasePresenter<View> {

    }
}