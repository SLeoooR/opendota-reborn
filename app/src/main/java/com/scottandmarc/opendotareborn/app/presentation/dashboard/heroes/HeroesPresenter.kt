package com.scottandmarc.opendotareborn.app.presentation.dashboard.heroes

import android.util.Log
import com.scottandmarc.opendotareborn.app.data.heroStats.HeroStatsRepository
import com.scottandmarc.opendotareborn.toolbox.helpers.CoroutineScopeProvider
import com.scottandmarc.opendotareborn.toolbox.retrofit.NetworkConnectionChecker
import kotlinx.coroutines.launch
import java.lang.Exception

class HeroesPresenter(
    private val coroutineScopeProvider: CoroutineScopeProvider,
    private val heroStatsRepository: HeroStatsRepository,
    private val networkConnectionChecker: NetworkConnectionChecker
) : HeroesContract.Presenter {

    private var view: HeroesContract.View? = null

    override fun onViewReady(view: HeroesContract.View) {
        this.view = view
        setup()
    }

    private fun setup() {
        coroutineScopeProvider.provide().launch {
            try {
                view?.showLoadingDialog()

                if (networkConnectionChecker.isNetworkAvailable()) {
                    val heroesStats = heroStatsRepository.fetchHeroesStats().sortedBy {
                        it.localizedName
                    }

                    view?.setHeroesStats(heroesStats)
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
}