package com.scottandmarc.opendotareborn.app.presentation.profile.overview

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.scottandmarc.opendotareborn.app.domain.entities.PlayerHero
import com.scottandmarc.opendotareborn.app.presentation.profile.heroes.PlayerHeroesListAdapter
import com.scottandmarc.opendotareborn.databinding.FragmentOverviewBinding
import com.scottandmarc.opendotareborn.di.DependencyInjector
import com.squareup.picasso.Picasso
import kotlinx.coroutines.runBlocking

class OverviewFragment : Fragment(), OverviewContract.View {

    private val binding: FragmentOverviewBinding by lazy {
        FragmentOverviewBinding.inflate(layoutInflater)
    }

    private lateinit var presenter: OverviewContract.Presenter
    private lateinit var playerHeroList: List<PlayerHero>
    private lateinit var rvPlayerHeroesAdapter: PlayerHeroesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPresenter(view.context)
        initRv(view.context)
    }

    private fun initPresenter(context: Context) {
        presenter = OverviewPresenter(
            DependencyInjector.providePlayerRepository(context),
            DependencyInjector.providePlayerHeroRepository(),
            DependencyInjector.provideHeroInfoRepository(context)
        )
        presenter.onViewReady(this)
    }

    override fun showProfilePic(profilePicURL: String) {
        Picasso.get().load(profilePicURL).into(binding.ivPlayerProfilePic)
    }

    override fun showPlayerRankPic(rankPicURL: String, starsPicURL: String) {
        Picasso.get().load(rankPicURL).into(binding.ivPlayerRankIcon)
        Picasso.get().load(starsPicURL).into(binding.ivPlayerStarIcon)
    }

    override fun showPlayerName(playerName: String) {
        binding.tvPlayerName.text = playerName
    }

    override fun showPlayerWins(wins: Int) {
        binding.tvPlayerWins.text = wins.toString()
    }

    override fun showPlayerLosses(losses: Int) {
        binding.tvPlayerLosses.text = losses.toString()
    }

    override fun showPlayerWinRate(winRate: Float) {
        val text = "${String.format("%.2f", winRate)}%"
        binding.tvPlayerWinRate.text = text
    }

    override suspend fun getPlayerHeroesList(playerHeroesList: List<PlayerHero>) {
        playerHeroList = playerHeroesList
    }

    private fun initRv(context: Context) {
        // Assign RV
        val rvPlayerHeroes = binding.topThreeHeroesLayout.rvPlayerHeroes

        runBlocking {
            val playerHeroRepository = DependencyInjector.providePlayerHeroRepository()

            playerHeroList = playerHeroRepository.fetchHeroes(131405121)

            //Init RecyclerView
            rvPlayerHeroesAdapter = PlayerHeroesListAdapter(context, playerHeroList, true)

            rvPlayerHeroes.layoutManager = LinearLayoutManager(context)
            rvPlayerHeroes.adapter = rvPlayerHeroesAdapter
        }
    }
}