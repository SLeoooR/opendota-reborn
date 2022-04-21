package com.scottandmarc.opendotareborn.app.presentation.dashboard.heroes.pro

import com.scottandmarc.opendotareborn.app.domain.entities.HeroStats

class ProPresenter(
    private val heroesStats: List<HeroStats>
) : ProContract.Presenter {

    private var view: ProContract.View? = null
    private var heroHeaderAscending = true
    private var pickHeaderAscending = true
    private var banHeaderAscending = true
    private var winHeaderAscending = true

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

    override fun onPickHeaderClicked() {
        val pickHeaderSorted = if (pickHeaderAscending) {
            pickHeaderAscending = false

            heroesStats.sortedBy {
                it.proPick
            }
        } else {
            pickHeaderAscending = true

            heroesStats.sortedByDescending {
                it.proPick
            }
        }

        view?.updateRv(pickHeaderSorted)
    }

    override fun onBanHeaderClicked() {
        val banHeaderSorted = if (banHeaderAscending) {
            banHeaderAscending = false

            heroesStats.sortedBy {
                it.proBan
            }
        } else {
            banHeaderAscending = true

            heroesStats.sortedByDescending {
                it.proBan
            }
        }

        view?.updateRv(banHeaderSorted)
    }

    override fun onWinHeaderClicked() {
        val winHeaderSorted = if (winHeaderAscending) {
            winHeaderAscending = false

            heroesStats.sortedBy {
                it.winRate
            }
        } else {
            winHeaderAscending = true

            heroesStats.sortedByDescending {
                it.winRate
            }
        }

        view?.updateRv(winHeaderSorted)
    }

    override fun onViewReady(view: ProContract.View) {
        this.view = view
    }

    override fun onViewDetach() {
        view = null
    }

}