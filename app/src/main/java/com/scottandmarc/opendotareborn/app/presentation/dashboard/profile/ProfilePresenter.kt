package com.scottandmarc.opendotareborn.app.presentation.dashboard.profile

import com.scottandmarc.opendotareborn.app.data.player.PlayerRepository
import com.scottandmarc.opendotareborn.app.domain.entities.Player
import com.scottandmarc.opendotareborn.app.domain.entities.PlayerHero
import com.scottandmarc.opendotareborn.toolbox.helpers.CoroutineScopeProvider
import com.scottandmarc.opendotareborn.toolbox.retrofit.NetworkConnectionChecker

class ProfilePresenter(
    private val accountId: Int,
    private val playerRepository: PlayerRepository,
    private val coroutineScopeProvider: CoroutineScopeProvider,
    private val networkConnectionChecker: NetworkConnectionChecker
) : ProfileContract.Presenter {

    private var view: ProfileContract.View? = null
    private lateinit var player: Player

    override fun onViewReady(view: ProfileContract.View) {
        this.view = view
        setup()
    }

    private fun setup() {
        if (accountId != 0) {
            player = playerRepository.getPlayer()
        } else {

        }
    }

    override fun onViewDetach() {
        view = null
    }

    override fun onBtnSteamIconClick() {
        view?.navigateToSteamProfile(player.profile.profileURL)
    }
}