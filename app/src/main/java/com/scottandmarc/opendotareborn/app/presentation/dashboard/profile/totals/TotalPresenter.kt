package com.scottandmarc.opendotareborn.app.presentation.dashboard.profile.totals

import android.util.Log
import com.scottandmarc.opendotareborn.app.data.player.PlayerRepository
import com.scottandmarc.opendotareborn.app.data.total.TotalRepository
import com.scottandmarc.opendotareborn.app.domain.entities.Total
import com.scottandmarc.opendotareborn.toolbox.helpers.CoroutineScopeProvider
import com.scottandmarc.opendotareborn.toolbox.retrofit.NetworkConnectionChecker
import kotlinx.coroutines.launch
import java.lang.Exception

class TotalPresenter(
    private val coroutineScopeProvider: CoroutineScopeProvider,
    private val playerRepository: PlayerRepository,
    private val totalRepository: TotalRepository,
    private val networkConnectionChecker: NetworkConnectionChecker
) : TotalContract.Presenter {

    private var view: TotalContract.View? = null
    private lateinit var totals: List<Total>

    override fun onViewReady(view: TotalContract.View) {
        this.view = view
        setup()
    }

    override fun onViewDetach() {
        view = null
    }

    private fun setup() {
        val accountId = playerRepository.getPlayer().profile.accountId
        coroutineScopeProvider.provide().launch {
            try {
                view?.showLoadingDialog()

                if (networkConnectionChecker.isNetworkAvailable()) {
                    totals = totalRepository.fetchTotals(accountId)

                    view?.setTotals(totals)
                    view?.updateRv()
                }

                view?.dismissLoadingDialog()
            } catch (e: Exception) {
                Log.d("error", e.localizedMessage?: "")
                throw e
            }
        }
    }
}