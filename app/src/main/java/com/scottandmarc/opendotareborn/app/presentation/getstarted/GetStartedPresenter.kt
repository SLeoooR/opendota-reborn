package com.scottandmarc.opendotareborn.app.presentation.getstarted

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