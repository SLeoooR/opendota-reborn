package com.scottandmarc.opendotareborn.app.presentation.profile.heroes

import com.scottandmarc.opendotareborn.app.domain.entities.PlayerHero
import com.scottandmarc.opendotareborn.toolbox.mvp.BasePresenter
import com.scottandmarc.opendotareborn.toolbox.mvp.BaseView

interface PlayerHeroesContract {
    interface View: BaseView {
        fun getPlayerHeroes(playerHeroes: List<PlayerHero>)
    }

    interface Presenter: BasePresenter<View> {

    }
}