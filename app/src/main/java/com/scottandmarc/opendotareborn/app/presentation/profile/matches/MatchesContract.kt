package com.scottandmarc.opendotareborn.app.presentation.profile.matches

import com.scottandmarc.opendotareborn.app.domain.entities.Match
import com.scottandmarc.opendotareborn.toolbox.mvp.BasePresenter
import com.scottandmarc.opendotareborn.toolbox.mvp.BaseView

interface MatchesContract {
    interface View: BaseView {
        fun setMatches(matches: List<Match>)
        fun setTotalPages(totalPages: Int)
        fun getCurrentPage(): Int
        fun setCurrentPage(currentPage: Int)
        fun updateRv()
        fun setupBtnNext()
        fun setupBtnPrev()
        fun toggleButtons()

        fun showLoadingDialog()
        fun dismissLoadingDialog()
    }

    interface Presenter: BasePresenter<View> {
        fun onPrevBtnClick()
        fun onNextBtnClick()
    }
}