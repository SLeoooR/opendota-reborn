package com.scottandmarc.opendotareborn.app.presentation.dashboard.teams

import android.util.Log
import com.scottandmarc.opendotareborn.app.data.teams.TeamRepository
import com.scottandmarc.opendotareborn.app.domain.entities.Team
import com.scottandmarc.opendotareborn.toolbox.helpers.CoroutineScopeProvider
import com.scottandmarc.opendotareborn.toolbox.retrofit.NetworkConnectionChecker
import kotlinx.coroutines.launch

class TeamsPresenter(
    private val coroutineScopeProvider: CoroutineScopeProvider,
    private val teamRepository: TeamRepository,
    private val networkConnectionChecker: NetworkConnectionChecker
) : TeamsContract.Presenter {

    private var view: TeamsContract.View? = null
    private lateinit var teams: List<Team>
    private lateinit var trimmedTeams: List<Team>

    override fun onViewReady(view: TeamsContract.View) {
        this.view = view
        setup()
    }

    private fun setup() {
        coroutineScopeProvider.provide().launch {
            try {
                view?.showLoadingDialog()

                if (networkConnectionChecker.isNetworkAvailable()) {
                    teams = teamRepository.fetchTeams()

                    trimmedTeams = teams.subList(0, 100)

                    view?.setTeams(trimmedTeams)
                    view?.updateRv()
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