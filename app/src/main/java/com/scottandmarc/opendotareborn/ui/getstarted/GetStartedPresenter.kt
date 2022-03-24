package com.scottandmarc.opendotareborn.ui.getstarted

class GetStartedPresenter : GetStartedContract.Presenter {
    private var view: GetStartedContract.View? = null

    override fun onGetStartedBtnClick() {
        view?.navigateToLogin()
    }

    override fun onViewReady(view: GetStartedContract.View) {
        this.view = view
    }

    override fun onViewDetach() {
        view = null
    }
}