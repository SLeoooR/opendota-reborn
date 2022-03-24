package com.scottandmarc.opendotareborn.ui.login

import android.util.Log
import com.scottandmarc.opendotareborn.data.ResponseCallback
import com.scottandmarc.opendotareborn.data.player.Player
import com.scottandmarc.opendotareborn.data.player.PlayerRepository
import com.scottandmarc.opendotareborn.data.player.RemotePlayer

class LoginPresenter(
    val playerRepository: PlayerRepository
) : LoginContract.Presenter {
    private var view: LoginContract.View? = null

    private lateinit var playerData: RemotePlayer

    override fun onContinueBtnClick(accountId: Int) {
        view?.showLoadingDialog()
        Log.d("accountId", accountId.toString())
        playerRepository.fetchPlayer(accountId, object : ResponseCallback<RemotePlayer> {
            override fun onSuccess(response: RemotePlayer) {
                playerData = response
                println(playerData)
//                playerRepository.insert(playerData)
//                view?.dismissLoadingDialog()
//                view?.navigateToProfile()
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