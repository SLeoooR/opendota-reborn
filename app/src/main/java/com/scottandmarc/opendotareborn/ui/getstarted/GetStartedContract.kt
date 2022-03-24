package com.scottandmarc.opendotareborn.ui.getstarted

import com.scottandmarc.opendotareborn.BasePresenter
import com.scottandmarc.opendotareborn.BaseView

interface GetStartedContract {
    interface View : BaseView {
        fun navigateToLogin()
    }

    interface Presenter : BasePresenter<View> {
        fun onGetStartedBtnClick()
    }
}