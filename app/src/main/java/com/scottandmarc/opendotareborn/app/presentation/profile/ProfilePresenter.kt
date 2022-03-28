package com.scottandmarc.opendotareborn.app.presentation.profile

import com.scottandmarc.opendotareborn.app.data.player.PlayerRepository
import com.scottandmarc.opendotareborn.app.domain.entities.Player

class ProfilePresenter(
    private val playerRepository: PlayerRepository
) : ProfileContract.Presenter {

    private var view: ProfileContract.View? = null
    private lateinit var player: Player

    override fun onViewReady(view: ProfileContract.View) {
        this.view = view
        player = playerRepository.getPlayer()
    }

    override fun onViewDetach() {
        view = null
    }

    override fun onBtnSteamIconClick() {
        view?.navigateToSteamProfile(player.profile?.profileURL ?: "")
    }
}