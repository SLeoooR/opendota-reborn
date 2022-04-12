package com.scottandmarc.opendotareborn.app.presentation.profile.peers

import com.scottandmarc.opendotareborn.app.domain.entities.Peer
import com.scottandmarc.opendotareborn.toolbox.mvp.BasePresenter
import com.scottandmarc.opendotareborn.toolbox.mvp.BaseView

interface PeersContract {
    interface View : BaseView {
        fun setPeers(peers: List<Peer>)
        fun setTotalPages(totalPages: Int)
        fun getCurrentPage(): Int
        fun setCurrentPage(currentPage: Int)
        fun updateRv()
        fun setupBtnNext()
        fun setupBtnPrev()
        fun toggleButtons()

        fun showLoadingDialog()
        fun dismissLoadingDialog()
    }

    interface Presenter : BasePresenter<View> {
        fun onPrevBtnClick()
        fun onNextBtnClick()
    }
}