package com.scottandmarc.opendotareborn.app.presentation.profile

import com.scottandmarc.opendotareborn.toolbox.mvp.BasePresenter
import com.scottandmarc.opendotareborn.toolbox.mvp.BaseView

interface ProfileContract {
    interface View : BaseView {
        fun displayToast(msg: String)

        fun navigateToSteamProfile(profileURL: String)

        fun showLoadingDialog()
        fun dismissLoadingDialog()
    }

    interface Presenter : BasePresenter<View> {
        fun onBtnSteamIconClick()
    }
}