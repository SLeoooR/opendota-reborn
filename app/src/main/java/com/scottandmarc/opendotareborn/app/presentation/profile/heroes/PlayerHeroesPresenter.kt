package com.scottandmarc.opendotareborn.app.presentation.profile.heroes

import android.util.Log
import com.scottandmarc.opendotareborn.app.data.hero.player.PlayerHeroRepository
import com.scottandmarc.opendotareborn.app.data.player.PlayerRepository
import com.scottandmarc.opendotareborn.app.domain.entities.PlayerHero
import com.scottandmarc.opendotareborn.toolbox.helpers.CoroutineScopeProvider
import com.scottandmarc.opendotareborn.toolbox.retrofit.NetworkConnectionChecker
import kotlinx.coroutines.launch

class PlayerHeroesPresenter(
    private val coroutineScopeProvider: CoroutineScopeProvider,
    private val playerRepository: PlayerRepository,
    private val playerHeroRepository: PlayerHeroRepository,
    private val networkConnectionChecker: NetworkConnectionChecker
) : PlayerHeroesContract.Presenter {

    private var view: PlayerHeroesContract.View? = null
    private lateinit var playerHeroes: List<PlayerHero>
    private lateinit var trimmedPlayerHeroes: List<PlayerHero>

    private val itemsPerPage = 20
    private var itemsRemaining = 0
    private var lastPage = 0

    override fun onViewReady(view: PlayerHeroesContract.View) {
        this.view = view
        setup()
    }

    private fun setup() {
        val accountId = playerRepository.getPlayer().profile.accountId
        coroutineScopeProvider.provide().launch {
            try {
                view?.showLoadingDialog()

                if (networkConnectionChecker.isNetworkAvailable()) {
                    playerHeroes = playerHeroRepository.fetchHeroes(accountId)

                    itemsRemaining = playerHeroes.size % itemsPerPage
                    lastPage = playerHeroes.size / itemsPerPage
                    trimmedPlayerHeroes = playerHeroes.subList(0, itemsPerPage)

                    view?.setPlayerHeroes(trimmedPlayerHeroes)
                    view?.updateRv()
                    view?.setTotalPages(lastPage)

                    view?.toggleButtons()
                    view?.setupBtnNext()
                    view?.setupBtnPrev()
                }

                view?.dismissLoadingDialog()
            } catch (e: Exception) {
                Log.d("error", e.localizedMessage?: "")
                throw e
            }
        }

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

    private fun generatePlayerHeroes(currentPage: Int) {
        val startItem = (currentPage * itemsPerPage) + 1
        val numOfData = itemsPerPage

        trimmedPlayerHeroes = if (currentPage == lastPage && itemsRemaining > 0) {
            playerHeroes.subList(startItem - 1, (startItem + itemsRemaining) - 1)
        } else {
            playerHeroes.subList(startItem - 1, (startItem + numOfData) - 1)
        }
        view?.setPlayerHeroes(trimmedPlayerHeroes)
    }
}