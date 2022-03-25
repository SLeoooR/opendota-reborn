package com.scottandmarc.opendotareborn.app.presentation.profile

class ProfilePresenter : ProfileContract.Presenter {

    private var view: ProfileContract.View? = null

    override fun onViewReady(view: ProfileContract.View) {
        this.view = view
    }

    override fun onViewDetach() {
        view = null
    }

    override fun onBtnSteamIconClick() {
        view?.displayToast("Steam Icon clicked.")
    }
}