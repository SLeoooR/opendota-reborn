package com.scottandmarc.opendotareborn.app.presentation.dashboard.heroes.turbo

import com.scottandmarc.opendotareborn.app.domain.entities.HeroStats

class TurboPresenter(
    private val heroesStats: List<HeroStats>
) : TurboContract.Presenter {

    private var view: TurboContract.View? = null
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

    override fun onPickHeaderClicked() {
        val pickHeaderSorted = if (pickHeaderAscending) {
            pickHeaderAscending = false

            heroesStats.sortedBy {
                it.turboPicks
            }
        } else {
            pickHeaderAscending = true

            heroesStats.sortedByDescending {
                it.turboPicks
            }
        }

        view?.updateRv(pickHeaderSorted)
    }

    override fun onWinHeaderClicked() {
        val winHeaderSorted = if (winHeaderAscending) {
            winHeaderAscending = false

            heroesStats.sortedBy {
                it.winRateTurbo
            }
        } else {
            winHeaderAscending = true

            heroesStats.sortedByDescending {
                it.winRateTurbo
            }
        }

        view?.updateRv(winHeaderSorted)
    }

    override fun onViewReady(view: TurboContract.View) {
        this.view = view
    }

    override fun onViewDetach() {
        view = null
    }

}