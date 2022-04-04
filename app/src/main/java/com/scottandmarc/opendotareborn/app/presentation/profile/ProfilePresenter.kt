package com.scottandmarc.opendotareborn.app.presentation.profile

import android.util.Log
import com.scottandmarc.opendotareborn.app.data.hero.player.PlayerHeroRepository
import com.scottandmarc.opendotareborn.app.data.player.PlayerRepository
import com.scottandmarc.opendotareborn.app.domain.entities.Player
import com.scottandmarc.opendotareborn.app.domain.entities.PlayerHero
import com.scottandmarc.opendotareborn.toolbox.helpers.CoroutineScopeProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProfilePresenter(
    private val coroutineScopeProvider: CoroutineScopeProvider,
    private val playerRepository: PlayerRepository,
    private val playerHeroRepository: PlayerHeroRepository,
) : ProfileContract.Presenter {

    private var view: ProfileContract.View? = null
    private lateinit var player: Player
    private lateinit var playerHeroes: List<PlayerHero>

    override fun onViewReady(view: ProfileContract.View) {
        this.view = view
        player = playerRepository.getPlayer()
        coroutineScopeProvider.provide().launch {
            try {
                playerHeroes = playerHeroRepository.fetchHeroes(player.profile.accountId)

                playerHeroes.forEach {
                    playerHeroRepository.insertPlayerHero(it)
                }
            } catch(t: Throwable) {
                t.printStackTrace()
            }
        }
    }

    override fun onViewDetach() {
        view = null
    }

    override fun onBtnSteamIconClick() {
        view?.navigateToSteamProfile(player.profile.profileURL)
    }
}