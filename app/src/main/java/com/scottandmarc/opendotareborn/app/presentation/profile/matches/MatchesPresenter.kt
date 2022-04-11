package com.scottandmarc.opendotareborn.app.presentation.profile.matches

import android.util.Log
import com.scottandmarc.opendotareborn.app.data.matches.MatchRepository
import com.scottandmarc.opendotareborn.app.data.player.PlayerRepository
import com.scottandmarc.opendotareborn.app.domain.entities.Match
import com.scottandmarc.opendotareborn.toolbox.helpers.CoroutineScopeProvider
import com.scottandmarc.opendotareborn.toolbox.retrofit.NetworkConnectionChecker
import kotlinx.coroutines.launch
import java.lang.Exception

class MatchesPresenter(
    private val coroutineScopeProvider: CoroutineScopeProvider,
    private val playerRepository: PlayerRepository,
    private val matchRepository: MatchRepository,
    private val networkConnectionChecker: NetworkConnectionChecker
) : MatchesContract.Presenter {

    private var view: MatchesContract.View? = null
    private lateinit var matches: List<Match>
    private lateinit var trimmedMatches: List<Match>

    private val itemsPerPage = 20
    private var itemsRemaining = 0
    private var lastPage = 0

    override fun onViewReady(view: MatchesContract.View) {
        this.view = view
        setup()
    }

    override fun onViewDetach() {
        view = null
    }

    private fun setup() {
        val accountId = playerRepository.getPlayer().profile.accountId
        coroutineScopeProvider.provide().launch {
            try {
                view?.showLoadingDialog()

                if (networkConnectionChecker.isNetworkAvailable()) {
                    matches = matchRepository.fetchMatches(accountId)

                    itemsRemaining = matches.size % itemsPerPage
                    lastPage = matches.size / itemsPerPage
                    trimmedMatches = matches.subList(0, itemsPerPage)

                    view?.setMatches(trimmedMatches)
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

    override fun onPrevBtnClick() {
        val currentPage = view?.getCurrentPage()!!

        generateMatches(currentPage)
    }

    override fun onNextBtnClick() {
        val currentPage = view?.getCurrentPage()!!

        generateMatches(currentPage)
    }

    private fun generateMatches(currentPage: Int) {
        val startItem = (currentPage * itemsPerPage) + 1
        val numOfData = itemsPerPage

        trimmedMatches = if (currentPage == lastPage && itemsRemaining > 0) {
            matches.subList(startItem - 1, (startItem + itemsRemaining) - 1)
        } else {
            matches.subList(startItem - 1, (startItem + numOfData) - 1)
        }
        view?.setMatches(trimmedMatches)
    }
}