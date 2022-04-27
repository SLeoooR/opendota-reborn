package com.scottandmarc.opendotareborn.app.presentation.login

import com.scottandmarc.opendotareborn.toolbox.mvp.BasePresenter
import com.scottandmarc.opendotareborn.toolbox.mvp.BaseView

interface LoginContract {
    interface View : BaseView {
        fun navigateToProfile()
        fun navigateToOpenDotaLogin()
        fun navigateToSearchPlayers()

        fun showLoadingDialog()
        fun dismissLoadingDialog()

        fun displayToast(msg: String)
    }

    interface Presenter : BasePresenter<View> {
        fun onContinueBtnClick(accountId: Int)
        fun onSearchBtnClick()
        fun onLoginViaOpenDotaBtnClick()
    }
}