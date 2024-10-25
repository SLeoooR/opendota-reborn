package com.scottandmarc.opendotareborn.app.presentation.dashboard.profile.totals

import android.util.Log
import com.scottandmarc.opendotareborn.app.data.player.PlayerRepository
import com.scottandmarc.opendotareborn.app.data.total.TotalRepository
import com.scottandmarc.opendotareborn.app.domain.entities.Total
import com.scottandmarc.opendotareborn.toolbox.helpers.CoroutineScopeProvider
import com.scottandmarc.opendotareborn.toolbox.retrofit.NetworkConnectionChecker
import kotlinx.coroutines.launch

class TotalPresenter(
    private val accountId: Int,
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
        val accountIdFinal = if (accountId == 0) {
            playerRepository.getPlayer().profile.accountId
        } else {
            accountId
        }
        coroutineScopeProvider.provide().launch {
            try {
                view?.showLoadingDialog()

                if (networkConnectionChecker.isNetworkAvailable()) {
                    totals = totalRepository.fetchTotals(accountIdFinal)

                    view?.setTotals(totals)
                    view?.updateRv()
                }

                view?.dismissLoadingDialog()
            } catch (e: Exception) {
                Log.d("error", e.localizedMessage?: "")
                view?.showOkayDialog(
                    title = "Error",
                    message = e.localizedMessage ?: "",
                    buttonText = "Okay"
                )
            }
        }
    }
}