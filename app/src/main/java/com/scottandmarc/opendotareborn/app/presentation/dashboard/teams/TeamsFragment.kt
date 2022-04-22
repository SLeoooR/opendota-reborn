package com.scottandmarc.opendotareborn.app.presentation.dashboard.teams

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.scottandmarc.opendotareborn.R
import com.scottandmarc.opendotareborn.app.domain.entities.ProcessedRecentMatch
import com.scottandmarc.opendotareborn.app.domain.entities.Team
import com.scottandmarc.opendotareborn.app.presentation.dashboard.profile.overview.RecentMatchesListAdapter
import com.scottandmarc.opendotareborn.databinding.FragmentTeamsBinding
import com.scottandmarc.opendotareborn.di.DependencyInjector
import com.scottandmarc.opendotareborn.toolbox.helpers.DialogHelper
import com.scottandmarc.opendotareborn.toolbox.retrofit.NetworkConnectionChecker

class TeamsFragment : Fragment(), TeamsContract.View {

    private val binding: FragmentTeamsBinding by lazy {
        FragmentTeamsBinding.inflate(layoutInflater)
    }

    private lateinit var presenter: TeamsContract.Presenter
    private lateinit var teams: List<Team>
    private lateinit var teamsListAdapter: TeamsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initPresenter()
    }

    private fun initPresenter() {
        presenter = TeamsPresenter(
            DependencyInjector.provideCoroutineScopeProvider(),
            DependencyInjector.provideTeamRepository(),
            NetworkConnectionChecker(requireContext())
        )
        presenter.onViewReady(this)
    }

    private fun initToolbar() {
        val toolbar = activity?.findViewById<Toolbar>(R.id.tbUserDashboardView)
        toolbar?.title = "Teams"
        toolbar?.setTitleTextColor(ContextCompat.getColor(requireContext(), R.color.white))
    }

    override fun showLoadingDialog() {
        binding.loadingLayout.visibility = View.VISIBLE
    }

    override fun dismissLoadingDialog() {
        binding.loadingLayout.visibility = View.INVISIBLE
    }

    override fun setTeams(teams: List<Team>) {
        this.teams = teams
    }

    override fun updateRv() {
        // Assign RV
        val rvPlayerHeroes = binding.rvTeams

        //Init RecyclerView
        teamsListAdapter = TeamsListAdapter(teams)

        rvPlayerHeroes.layoutManager = LinearLayoutManager(this.context)
        rvPlayerHeroes.adapter = teamsListAdapter
    }
}