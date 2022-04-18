package com.scottandmarc.opendotareborn.app.presentation.dashboard.settings

import androidx.fragment.app.FragmentManager
import com.scottandmarc.opendotareborn.toolbox.mvp.BasePresenter
import com.scottandmarc.opendotareborn.toolbox.mvp.BaseView

interface SettingsContract {
    interface View : BaseView {

    }

    interface Presenter: BasePresenter<View> {
        fun onChangeCodeBtnClick()
    }
}