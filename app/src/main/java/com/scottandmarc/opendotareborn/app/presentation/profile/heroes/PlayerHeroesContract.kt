package com.scottandmarc.opendotareborn.app.presentation.profile.heroes

import com.scottandmarc.opendotareborn.app.domain.entities.PlayerHero
import com.scottandmarc.opendotareborn.toolbox.mvp.BasePresenter
import com.scottandmarc.opendotareborn.toolbox.mvp.BaseView

interface PlayerHeroesContract {
    interface View: BaseView {
        fun setPlayerHeroes(playerHeroes: List<PlayerHero>)
        fun getCurrentPage(): Int
        fun setCurrentPage(currentPage: Int)
        fun updateRv()

        fun showLoadingDialog()
        fun dismissLoadingDialog()

        fun setTotalPages(totalPages: Int)
        fun toggleButtons()
        fun setupBtnNext()
        fun setupBtnPrev()
    }

    interface Presenter: BasePresenter<View> {
        fun onPrevBtnClick()
        fun onNextBtnClick()
    }
}