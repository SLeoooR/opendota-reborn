package com.scottandmarc.opendotareborn.app.presentation.login

import android.util.Log
import com.scottandmarc.opendotareborn.app.domain.entities.Player
import com.scottandmarc.opendotareborn.app.domain.gateways.PlayerGateway
import com.scottandmarc.opendotareborn.toolbox.helpers.CoroutineScopeProvider
import kotlinx.coroutines.launch

class LoginPresenter(
    private val playerGateway: PlayerGateway,
    private val coroutineScopeProvider: CoroutineScopeProvider
) : LoginContract.Presenter {
    private var view: LoginContract.View? = null

    private lateinit var playerData: Player

    override fun onContinueBtnClick(accountId: Int) {
        coroutineScopeProvider.provide().launch {
            try {
                view?.showLoadingDialog()
                Log.d("accountId", accountId.toString())
                playerData = playerGateway.fetchPlayer(accountId)
                playerGateway.insert(playerData)

                view?.dismissLoadingDialog()
                view?.navigateToProfile()
            } catch (e: Exception) {
                view?.dismissLoadingDialog()
                view?.displayToast(e.localizedMessage?: "")
                Log.d("error", e.localizedMessage?: "")
            }
        }
    }

    override fun onViewReady(view: LoginContract.View) {
        this.view = view
    }

    override fun onViewDetach() {
        view = null
    }
}