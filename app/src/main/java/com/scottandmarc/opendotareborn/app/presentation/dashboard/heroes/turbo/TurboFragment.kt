package com.scottandmarc.opendotareborn.app.presentation.dashboard.heroes.turbo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.scottandmarc.opendotareborn.app.domain.entities.HeroStats
import com.scottandmarc.opendotareborn.databinding.FragmentTurboBinding

class TurboFragment(
    private val heroesStats: List<HeroStats>
) : Fragment(), TurboContract.View {

    private var totalTurboPick = 0

    private lateinit var presenter: TurboContract.Presenter

    private val binding: FragmentTurboBinding by lazy {
        FragmentTurboBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        heroesStats.forEach {
            totalTurboPick += it.turboPicks
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRv()
        initPresenter()
        initViews()
    }

    private fun initViews() {
        binding.tvHeroHeader.setOnClickListener{
            presenter.onHeroHeaderClicked()
        }
        binding.tvPickHeader.setOnClickListener {
            presenter.onPickHeaderClicked()
        }
        binding.tvWinHeader.setOnClickListener {
            presenter.onWinHeaderClicked()
        }
    }

    private fun initPresenter() {
        presenter = TurboPresenter(
            heroesStats
        )
        presenter.onViewReady(this)
    }

    private fun initRv() {
        val rvMatches = binding.rvPlayerHeroes

        val rvTurboHeroesListAdapter = TurboHeroesListAdapter(heroesStats, totalTurboPick)

        rvMatches.layoutManager = LinearLayoutManager(this.context)
        rvMatches.adapter = rvTurboHeroesListAdapter
    }

    override fun updateRv(sortedHeroesStats: List<HeroStats>) {
        val rvMatches = binding.rvPlayerHeroes

        val rvTurboHeroesListAdapter = TurboHeroesListAdapter(sortedHeroesStats, totalTurboPick)

        rvMatches.layoutManager = LinearLayoutManager(this.context)
        rvMatches.adapter = rvTurboHeroesListAdapter
    }
}