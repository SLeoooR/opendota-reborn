package com.scottandmarc.opendotareborn.app.presentation.dashboard.heroes.pub

import com.scottandmarc.opendotareborn.app.domain.entities.HeroStats

class PubPresenter(
    private val heroesStats: List<HeroStats>
) : PubContract.Presenter {

    private var view: PubContract.View? = null
    private var heroHeaderAscending = false
    private var pickHeaderAscending = false
    private var winHeaderAscending = false

    override fun onHeroHeaderClicked() {
        val heroHeaderSorted = if (heroHeaderAscending) {
            heroHeaderAscending = false

            heroesStats.sortedBy {
                it.localizedName
            }
        } else {
            heroHeaderAscending = true

            heroesStats.sortedByDescending {
                it.localizedName
            }
        }

        view?.updateRv(heroHeaderSorted)
    }

    override fun onPickHeaderClicked(category: String) {
        val pickHeaderSorted = if (pickHeaderAscending) {
            pickHeaderAscending = false

            heroesStats.sortedBy {
                when (category) {
                    "all" -> {
                        it.overallPicks
                    }
                    "herald" -> {
                        it.heraldPick
                    }
                    "guardian" -> {
                        it.guardianPick
                    }
                    "crusader" -> {
                        it.crusaderPick
                    }
                    "archon" -> {
                        it.archonPick
                    }
                    "legend" -> {
                        it.legendPick
                    }
                    "ancient" -> {
                        it.ancientPick
                    }
                    "divine" -> {
                        it.divinePick
                    }
                    "immortal" -> {
                        it.immortalPick
                    }
                    else -> {
                        throw Exception("No category found")
                    }
                }
            }
        } else {
            pickHeaderAscending = true

            heroesStats.sortedByDescending {
                when (category) {
                    "all" -> {
                        it.overallPicks
                    }
                    "herald" -> {
                        it.heraldPick
                    }
                    "guardian" -> {
                        it.guardianPick
                    }
                    "crusader" -> {
                        it.crusaderPick
                    }
                    "archon" -> {
                        it.archonPick
                    }
                    "legend" -> {
                        it.legendPick
                    }
                    "ancient" -> {
                        it.ancientPick
                    }
                    "divine" -> {
                        it.divinePick
                    }
                    "immortal" -> {
                        it.immortalPick
                    }
                    else -> {
                        throw Exception("No category found")
                    }
                }
            }
        }

        view?.updateRv(pickHeaderSorted)
    }

    override fun onWinHeaderClicked(category: String) {
        val winHeaderSorted = if (winHeaderAscending) {
            winHeaderAscending = false

            heroesStats.sortedBy {
                when (category) {
                    "all" -> {
                        it.winRateOverall
                    }
                    "herald" -> {
                        it.winRateHerald
                    }
                    "guardian" -> {
                        it.winRateGuardian
                    }
                    "crusader" -> {
                        it.winRateCrusader
                    }
                    "archon" -> {
                        it.winRateArchon
                    }
                    "legend" -> {
                        it.winRateLegend
                    }
                    "ancient" -> {
                        it.winRateAncient
                    }
                    "divine" -> {
                        it.winRateDivine
                    }
                    "immortal" -> {
                        it.winRateImmortal
                    }
                    else -> {
                        throw Exception("No category found")
                    }
                }
            }
        } else {
            winHeaderAscending = true

            heroesStats.sortedByDescending {
                when (category) {
                    "all" -> {
                        it.winRateOverall
                    }
                    "herald" -> {
                        it.winRateHerald
                    }
                    "guardian" -> {
                        it.winRateGuardian
                    }
                    "crusader" -> {
                        it.winRateCrusader
                    }
                    "archon" -> {
                        it.winRateArchon
                    }
                    "legend" -> {
                        it.winRateLegend
                    }
                    "ancient" -> {
                        it.winRateAncient
                    }
                    "divine" -> {
                        it.winRateDivine
                    }
                    "immortal" -> {
                        it.winRateImmortal
                    }
                    else -> {
                        throw Exception("No category found")
                    }
                }
            }
        }

        view?.updateRv(winHeaderSorted)
    }

    private fun btnRankClicked(category: String) {
        view?.updateCategory(category)
        view?.updateRv(heroesStats)
        heroHeaderAscending = false
        pickHeaderAscending = false
        winHeaderAscending = false
    }

    override fun onAllBtnClicked() {
        btnRankClicked("all")
    }

    override fun onHeraldBtnClicked() {
        btnRankClicked("herald")
    }

    override fun onGuardianBtnClicked() {
        btnRankClicked("guardian")
    }

    override fun onCrusaderBtnClicked() {
        btnRankClicked("crusader")
    }

    override fun onArchonBtnClicked() {
        btnRankClicked("archon")
    }

    override fun onLegendBtnClicked() {
        btnRankClicked("legend")
    }

    override fun onAncientBtnClicked() {
        btnRankClicked("ancient")
    }

    override fun onDivineBtnClicked() {
        btnRankClicked("divine")
    }

    override fun onImmortalBtnClicked() {
        btnRankClicked("immortal")
    }

    override fun onViewReady(view: PubContract.View) {
        this.view = view
    }

    override fun onViewDetach() {
        view = null
    }
}