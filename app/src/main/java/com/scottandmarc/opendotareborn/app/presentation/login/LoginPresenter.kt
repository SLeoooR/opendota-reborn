package com.scottandmarc.opendotareborn.app.presentation.login

import android.util.Log
import com.scottandmarc.opendotareborn.app.domain.entities.Player
import com.scottandmarc.opendotareborn.app.domain.gateways.PlayerGateway
import com.scottandmarc.opendotareborn.toolbox.helpers.CoroutineScopeProvider
import com.scottandmarc.opendotareborn.toolbox.retrofit.NetworkConnectionChecker
import kotlinx.coroutines.launch

class LoginPresenter(
    private val coroutineScopeProvider: CoroutineScopeProvider,
    private val playerGateway: PlayerGateway,
    private val networkConnectionChecker: NetworkConnectionChecker
) : LoginContract.Presenter {
    private var view: LoginContract.View? = null

    private lateinit var playerData: Player

    override fun onContinueBtnClick(accountId: Int) {
        coroutineScopeProvider.provide().launch {
            try {
                view?.showLoadingDialog()
                Log.d("accountId", accountId.toString())

                if (networkConnectionChecker.isNetworkAvailable()) {
                    playerData = playerGateway.fetchPlayer(accountId)

                    val playerNotExist =
                        playerData.trackedUntil == null &&
                        playerData.soloCompetitiveRank == null &&
                        playerData.leaderboardRank == null &&
                        playerData.competitiveRank == null &&
                        playerData.rankTier == null &&
                        playerData.mmrEstimate.estimate == null

                    if (playerNotExist) {
                        view?.displayToast("Player does not exist")
                    } else {
                        playerGateway.insert(playerData)

                        view?.navigateToProfile()
                    }
                } else {
                    view?.displayToast("No internet connection")
                }

                view?.dismissLoadingDialog()
            } catch (e: Exception) {
                view?.dismissLoadingDialog()
                view?.displayToast(e.localizedMessage?: "")
                Log.d("error", e.localizedMessage?: "")
            }
        }
    }

    override fun onSearchBtnClick() {
        view?.navigateToSearchPlayers()
    }

    override fun onLoginViaOpenDotaBtnClick() {
        view?.navigateToOpenDotaLogin()
    }

    override fun onViewReady(view: LoginContract.View) {
        this.view = view
    }

    override fun onViewDetach() {
        view = null
    }
}