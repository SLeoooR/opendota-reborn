package com.scottandmarc.opendotareborn

interface BasePresenter<T : BaseView> {
    fun onViewReady(view: T)
    fun onViewDetach()
}