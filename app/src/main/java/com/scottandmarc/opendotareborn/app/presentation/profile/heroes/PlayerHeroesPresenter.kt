package com.scottandmarc.opendotareborn.app.presentation.profile.heroes

import com.scottandmarc.opendotareborn.app.data.hero.player.PlayerHeroRepository
import com.scottandmarc.opendotareborn.app.domain.entities.PlayerHero

class PlayerHeroesPresenter(
    private val playerHeroRepository: PlayerHeroRepository
) : PlayerHeroesContract.Presenter {

    private var view: PlayerHeroesContract.View? = null
    private lateinit var allPlayerHeroes: List<PlayerHero>
    private lateinit var playerHeroes: List<PlayerHero>

    private val itemsPerPage = 20
    private var totalHeroes = 0
    private var itemsRemaining = 0
    private var lastPage = 0

    override fun onViewReady(view: PlayerHeroesContract.View) {
        this.view = view
        setup()
    }

    private fun setup() {
        allPlayerHeroes = playerHeroRepository.getPlayerHeroes()
        totalHeroes = allPlayerHeroes.size
        itemsRemaining = totalHeroes % itemsPerPage
        lastPage = totalHeroes / itemsPerPage
        playerHeroes = allPlayerHeroes.subList(0, itemsPerPage)
        view?.setPlayerHeroes(playerHeroes)
    }

    override fun onViewDetach() {
        view = null
    }

    override fun onPrevBtnClick() {
        val currentPage = view?.getCurrentPage()!!

        generatePlayerHeroes(currentPage)
    }

    override fun onNextBtnClick() {
        val currentPage = view?.getCurrentPage()!!

        generatePlayerHeroes(currentPage)
    }

    override fun getTotalPages(): Int = lastPage

    private fun generatePlayerHeroes(currentPage: Int) {
        val startItem = (currentPage * itemsPerPage) + 1
        val numOfData = itemsPerPage

        playerHeroes = if (currentPage == lastPage && itemsRemaining > 0) {
            allPlayerHeroes.subList(startItem - 1, (startItem + itemsRemaining) - 1)
        } else {
            allPlayerHeroes.subList(startItem - 1, (startItem + numOfData) - 1)
        }
        view?.setPlayerHeroes(playerHeroes)
    }
}