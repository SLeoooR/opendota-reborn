package com.scottandmarc.opendotareborn.app.presentation.profile.heroes

import com.scottandmarc.opendotareborn.app.data.hero.player.PlayerHeroRepository
import com.scottandmarc.opendotareborn.app.domain.entities.PlayerHero

class PlayerHeroesPresenter(
    private val playerHeroRepository: PlayerHeroRepository
) : PlayerHeroesContract.Presenter {

    private var view: PlayerHeroesContract.View? = null
    private lateinit var playerHeroes: List<PlayerHero>

    override fun onViewReady(view: PlayerHeroesContract.View) {
        this.view = view
        setup()
    }

    private fun setup() {
        playerHeroes = playerHeroRepository.getPlayerHeroes()
        view?.getPlayerHeroes(playerHeroes)
    }

    override fun onViewDetach() {
        view = null
    }
}