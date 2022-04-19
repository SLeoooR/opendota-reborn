package com.scottandmarc.opendotareborn.app.presentation.dashboard.teams

import com.scottandmarc.opendotareborn.app.domain.entities.Team
import com.scottandmarc.opendotareborn.toolbox.mvp.BasePresenter
import com.scottandmarc.opendotareborn.toolbox.mvp.BaseView

interface TeamsContract {
    interface View : BaseView {
        fun showLoadingDialog()
        fun dismissLoadingDialog()

        fun setTeams(teams: List<Team>)
        fun updateRv()
    }

    interface Presenter : BasePresenter<View> {

    }
}