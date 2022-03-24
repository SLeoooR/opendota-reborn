package com.scottandmarc.opendotareborn.ui.login

import com.scottandmarc.opendotareborn.BasePresenter
import com.scottandmarc.opendotareborn.BaseView

interface LoginContract {
    interface View : BaseView {
        fun navigateToProfile()

        fun showLoadingDialog()
        fun dismissLoadingDialog()

        fun displayToast(msg: String)
    }

    interface Presenter : BasePresenter<View> {
        fun onContinueBtnClick(accountId: Int)
    }
}