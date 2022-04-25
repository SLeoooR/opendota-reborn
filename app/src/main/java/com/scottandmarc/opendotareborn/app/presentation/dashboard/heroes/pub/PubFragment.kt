package com.scottandmarc.opendotareborn.app.presentation.dashboard.heroes.pub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.scottandmarc.opendotareborn.R
import com.scottandmarc.opendotareborn.app.domain.entities.HeroStats
import com.scottandmarc.opendotareborn.app.presentation.dashboard.heroes.turbo.TurboContract
import com.scottandmarc.opendotareborn.app.presentation.dashboard.heroes.turbo.TurboHeroesListAdapter
import com.scottandmarc.opendotareborn.app.presentation.dashboard.heroes.turbo.TurboPresenter
import com.scottandmarc.opendotareborn.databinding.FragmentPubBinding
import com.scottandmarc.opendotareborn.databinding.FragmentTurboBinding
import com.squareup.picasso.Picasso

class PubFragment(
    private val heroesStats: List<HeroStats>
) : Fragment(), PubContract.View {

    private var totalPubPick = 0
    private var totalHeraldPick = 0
    private var totalGuardianPick = 0
    private var totalCrusaderPick = 0
    private var totalArchonPick = 0
    private var totalLegendPick = 0
    private var totalAncientPick = 0
    private var totalDivinePick = 0
    private var totalImmortalPick = 0

    private lateinit var totalPicksPerRank: List<Int>

    private lateinit var presenter: PubContract.Presenter
    private var category = "all"

    private val binding: FragmentPubBinding by lazy {
        FragmentPubBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        heroesStats.forEach {
            totalPubPick += it.overallPicks
            totalHeraldPick += it.heraldPick
            totalGuardianPick += it.guardianPick
            totalCrusaderPick += it.crusaderPick
            totalArchonPick += it.archonPick
            totalLegendPick += it.legendPick
            totalAncientPick += it.ancientPick
            totalDivinePick += it.divinePick
            totalImmortalPick += it.immortalPick
        }

        totalPicksPerRank = listOf(
            totalPubPick,
            totalHeraldPick,
            totalGuardianPick,
            totalCrusaderPick,
            totalArchonPick,
            totalLegendPick,
            totalAncientPick,
            totalDivinePick,
            totalImmortalPick,
        )

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
            presenter.onPickHeaderClicked(category)
        }
        binding.tvWinHeader.setOnClickListener {
            presenter.onWinHeaderClicked(category)
        }

        Picasso.get().load("https://www.opendota.com/assets/images/dota2/rank_icons/rank_icon_1.png").error(R.drawable.ic_question_mark).placeholder(R.drawable.ic_question_mark).into(binding.btnHerald)
        Picasso.get().load("https://www.opendota.com/assets/images/dota2/rank_icons/rank_icon_2.png").error(R.drawable.ic_question_mark).placeholder(R.drawable.ic_question_mark).into(binding.btnGuardian)
        Picasso.get().load("https://www.opendota.com/assets/images/dota2/rank_icons/rank_icon_3.png").error(R.drawable.ic_question_mark).placeholder(R.drawable.ic_question_mark).into(binding.btnCrusader)
        Picasso.get().load("https://www.opendota.com/assets/images/dota2/rank_icons/rank_icon_4.png").error(R.drawable.ic_question_mark).placeholder(R.drawable.ic_question_mark).into(binding.btnArchon)
        Picasso.get().load("https://www.opendota.com/assets/images/dota2/rank_icons/rank_icon_5.png").error(R.drawable.ic_question_mark).placeholder(R.drawable.ic_question_mark).into(binding.btnLegend)
        Picasso.get().load("https://www.opendota.com/assets/images/dota2/rank_icons/rank_icon_6.png").error(R.drawable.ic_question_mark).placeholder(R.drawable.ic_question_mark).into(binding.btnAncient)
        Picasso.get().load("https://www.opendota.com/assets/images/dota2/rank_icons/rank_icon_7.png").error(R.drawable.ic_question_mark).placeholder(R.drawable.ic_question_mark).into(binding.btnDivine)
        Picasso.get().load("https://www.opendota.com/assets/images/dota2/rank_icons/rank_icon_8.png").error(R.drawable.ic_question_mark).placeholder(R.drawable.ic_question_mark).into(binding.btnImmortal)

        fun clearBtnAlphas() {
            binding.btnAll.alpha = 0.5F
            binding.btnHerald.alpha = 0.5F
            binding.btnGuardian.alpha = 0.5F
            binding.btnCrusader.alpha = 0.5F
            binding.btnArchon.alpha = 0.5F
            binding.btnLegend.alpha = 0.5F
            binding.btnAncient.alpha = 0.5F
            binding.btnDivine.alpha = 0.5F
            binding.btnImmortal.alpha = 0.5F
        }

        clearBtnAlphas()
        binding.btnAll.alpha = 1F

        binding.btnAll.setOnClickListener {
            presenter.onAllBtnClicked()
            clearBtnAlphas()
            binding.btnAll.alpha = 1F
        }

        binding.btnHerald.setOnClickListener {
            presenter.onHeraldBtnClicked()
            clearBtnAlphas()
            binding.btnHerald.alpha = 1F
        }

        binding.btnGuardian.setOnClickListener {
            presenter.onGuardianBtnClicked()
            clearBtnAlphas()
            binding.btnGuardian.alpha = 1F
        }

        binding.btnCrusader.setOnClickListener {
            presenter.onCrusaderBtnClicked()
            clearBtnAlphas()
            binding.btnCrusader.alpha = 1F
        }

        binding.btnArchon.setOnClickListener {
            presenter.onArchonBtnClicked()
            clearBtnAlphas()
            binding.btnArchon.alpha = 1F
        }

        binding.btnLegend.setOnClickListener {
            presenter.onLegendBtnClicked()
            clearBtnAlphas()
            binding.btnLegend.alpha = 1F
        }

        binding.btnAncient.setOnClickListener {
            presenter.onAncientBtnClicked()
            clearBtnAlphas()
            binding.btnAncient.alpha = 1F
        }

        binding.btnDivine.setOnClickListener {
            presenter.onDivineBtnClicked()
            clearBtnAlphas()
            binding.btnDivine.alpha = 1F
        }

        binding.btnImmortal.setOnClickListener {
            presenter.onImmortalBtnClicked()
            clearBtnAlphas()
            binding.btnImmortal.alpha = 1F
        }
    }

    private fun initPresenter() {
        presenter = PubPresenter(
            heroesStats
        )
        presenter.onViewReady(this)
    }

    private fun initRv() {
        val rvMatches = binding.rvPlayerHeroes

        val rvPubHeroesListAdapter = PubHeroesListAdapter(heroesStats, totalPicksPerRank, category)

        rvMatches.layoutManager = LinearLayoutManager(this.context)
        rvMatches.adapter = rvPubHeroesListAdapter
    }

    override fun updateRv(sortedHeroesStats: List<HeroStats>) {
        val rvMatches = binding.rvPlayerHeroes

        val rvPubHeroesListAdapter = PubHeroesListAdapter(sortedHeroesStats, totalPicksPerRank, category)

        rvMatches.layoutManager = LinearLayoutManager(this.context)
        rvMatches.adapter = rvPubHeroesListAdapter
    }

    override fun updateCategory(category: String) {
        this.category = category
    }
}