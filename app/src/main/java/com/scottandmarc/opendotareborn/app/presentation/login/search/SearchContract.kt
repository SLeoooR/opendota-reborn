package com.scottandmarc.opendotareborn.app.presentation.login.search

import com.scottandmarc.opendotareborn.app.domain.entities.Search
import com.scottandmarc.opendotareborn.toolbox.mvp.BasePresenter
import com.scottandmarc.opendotareborn.toolbox.mvp.BaseView

interface SearchContract {
    interface View : BaseView {
        fun showLoadingDialog()
        fun dismissLoadingDialog()

        fun setSearches(searches: List<Search>)
        fun updateRv()
    }

    interface Presenter : BasePresenter<View> {
        fun onBtnSearchClicked(query: String)
    }
}