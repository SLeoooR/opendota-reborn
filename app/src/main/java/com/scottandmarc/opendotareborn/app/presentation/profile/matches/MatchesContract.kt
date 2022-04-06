package com.scottandmarc.opendotareborn.app.presentation.profile.matches

import com.scottandmarc.opendotareborn.app.domain.entities.Match
import com.scottandmarc.opendotareborn.toolbox.mvp.BasePresenter
import com.scottandmarc.opendotareborn.toolbox.mvp.BaseView

interface MatchesContract {
    interface View: BaseView {
        fun setMatches(matches: List<Match>)
        fun getCurrentPage(): Int
        fun setCurrentPage(currentPage: Int)
        fun updateRv()
    }

    interface Presenter: BasePresenter<View> {
        fun onPrevBtnClick()
        fun onNextBtnClick()
        fun getTotalPages(): Int
    }
}