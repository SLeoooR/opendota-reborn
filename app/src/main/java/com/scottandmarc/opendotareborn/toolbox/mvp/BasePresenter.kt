package com.scottandmarc.opendotareborn.toolbox.mvp

interface BasePresenter<T : BaseView> {
    fun onViewReady(view: T)
    fun onViewDetach()
}