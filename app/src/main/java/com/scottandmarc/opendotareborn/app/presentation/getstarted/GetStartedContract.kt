package com.scottandmarc.opendotareborn.app.presentation.getstarted

import com.scottandmarc.opendotareborn.toolbox.mvp.BasePresenter
import com.scottandmarc.opendotareborn.toolbox.mvp.BaseView

interface GetStartedContract {
    interface View : BaseView {
        fun navigateToLogin()
    }

    interface Presenter : BasePresenter<View> {
        fun onGetStartedBtnClick()
    }
}