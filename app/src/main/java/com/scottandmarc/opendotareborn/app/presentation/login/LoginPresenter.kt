package com.scottandmarc.opendotareborn.app.presentation.login

import android.util.Log
import com.scottandmarc.opendotareborn.toolbox.helpers.ResponseCallback
import com.scottandmarc.opendotareborn.app.domain.gateways.PlayerGateway
import com.scottandmarc.opendotareborn.app.domain.entities.Player

class LoginPresenter(
    val playerGateway: PlayerGateway
) : LoginContract.Presenter {
    private var view: LoginContract.View? = null

    private lateinit var playerData: Player

    override fun onContinueBtnClick(accountId: Int) {
        view?.showLoadingDialog()
        Log.d("accountId", accountId.toString())
        playerGateway.fetchPlayer(accountId, object : ResponseCallback<Player> {
            override fun onSuccess(response: Player) {
                playerData = response
                playerGateway.insert(playerData)
                view?.dismissLoadingDialog()
                view?.navigateToProfile()
            }

            override fun onFailure(msg: String) {
                view?.dismissLoadingDialog()
                view?.displayToast(msg)
            }
        })
    }

    override fun onViewReady(view: LoginContract.View) {
        this.view = view
    }

    override fun onViewDetach() {
        view = null
    }
}