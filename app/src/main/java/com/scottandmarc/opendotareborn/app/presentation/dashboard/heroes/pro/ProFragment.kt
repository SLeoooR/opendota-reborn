package com.scottandmarc.opendotareborn.app.presentation.dashboard.heroes.pro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.scottandmarc.opendotareborn.app.domain.entities.HeroStats
import com.scottandmarc.opendotareborn.databinding.FragmentProBinding

class ProFragment(
    private val heroesStats: List<HeroStats>
) : Fragment(), ProContract.View {

    private var totalProPick = 0

    private lateinit var presenter: ProContract.Presenter

    private val binding: FragmentProBinding by lazy {
        FragmentProBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        heroesStats.forEach {
            totalProPick += it.proPick
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
        binding.tvBanHeader.setOnClickListener {
            presenter.onBanHeaderClicked()
        }
        binding.tvWinHeader.setOnClickListener {
            presenter.onWinHeaderClicked()
        }
    }

    private fun initPresenter() {
        presenter = ProPresenter(
            heroesStats
        )
        presenter.onViewReady(this)
    }

    private fun initRv() {
        val rvMatches = binding.rvPlayerHeroes

        val rvProHeroesListAdapter = ProHeroesListAdapter(heroesStats, totalProPick)

        rvMatches.layoutManager = LinearLayoutManager(this.context)
        rvMatches.adapter = rvProHeroesListAdapter
    }

    override fun updateRv(sortedHeroesStats: List<HeroStats>) {
        val rvMatches = binding.rvPlayerHeroes

        val rvProHeroesListAdapter = ProHeroesListAdapter(sortedHeroesStats, totalProPick)

        rvMatches.layoutManager = LinearLayoutManager(this.context)
        rvMatches.adapter = rvProHeroesListAdapter
    }
}