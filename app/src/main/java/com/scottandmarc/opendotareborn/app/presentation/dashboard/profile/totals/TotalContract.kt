package com.scottandmarc.opendotareborn.app.presentation.dashboard.profile.totals

import com.scottandmarc.opendotareborn.app.domain.entities.Total
import com.scottandmarc.opendotareborn.toolbox.mvp.BasePresenter
import com.scottandmarc.opendotareborn.toolbox.mvp.BaseView

interface TotalContract {
    interface View : BaseView {
        fun setTotals(totals: List<Total>)
        fun updateRv()

        fun showLoadingDialog()
        fun dismissLoadingDialog()
    }

    interface Presenter : BasePresenter<View> {
    }
}