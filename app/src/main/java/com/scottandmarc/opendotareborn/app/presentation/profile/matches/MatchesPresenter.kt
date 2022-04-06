package com.scottandmarc.opendotareborn.app.presentation.profile.matches

import com.scottandmarc.opendotareborn.app.data.matches.MatchRepository
import com.scottandmarc.opendotareborn.app.domain.entities.Match

class MatchesPresenter(
    private val matchRepository: MatchRepository
) : MatchesContract.Presenter {

    private var view: MatchesContract.View? = null
    private lateinit var allMatches: List<Match>
    private lateinit var matches: List<Match>

    private val itemsPerPage = 20
    private var totalMatches = 0
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
        allMatches = matchRepository.getMatches()
        totalMatches = allMatches.size
        itemsRemaining = totalMatches % itemsPerPage
        lastPage = totalMatches / itemsPerPage
        matches = allMatches.subList(0, itemsPerPage)
        view?.setMatches(matches)
    }

    override fun onPrevBtnClick() {
        val currentPage = view?.getCurrentPage()!!

        generateMatches(currentPage)
    }

    override fun onNextBtnClick() {
        val currentPage = view?.getCurrentPage()!!

        generateMatches(currentPage)
    }

    override fun getTotalPages(): Int = lastPage

    private fun generateMatches(currentPage: Int) {
        val startItem = (currentPage * itemsPerPage) + 1
        val numOfData = itemsPerPage

        matches = if (currentPage == lastPage && itemsRemaining > 0) {
            allMatches.subList(startItem - 1, (startItem + itemsRemaining) - 1)
        } else {
            allMatches.subList(startItem - 1, (startItem + numOfData) - 1)
        }
        view?.setMatches(matches)
    }
}