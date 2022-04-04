package com.scottandmarc.opendotareborn.app.presentation.profile.heroes

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.scottandmarc.opendotareborn.R
import com.scottandmarc.opendotareborn.app.domain.entities.PlayerHero
import com.scottandmarc.opendotareborn.databinding.FragmentPlayerHeroesBinding
import com.scottandmarc.opendotareborn.di.DependencyInjector

class PlayerHeroesFragment : Fragment(), PlayerHeroesContract.View {

    private val binding: FragmentPlayerHeroesBinding by lazy {
        FragmentPlayerHeroesBinding.inflate(layoutInflater)
    }

    private lateinit var presenter: PlayerHeroesContract.Presenter
    private lateinit var rvPlayerHeroesAdapter: PlayerHeroesListAdapter
    private lateinit var playerHeroes: List<PlayerHero>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPresenter(view.context)
        initRv()
    }

    private fun initPresenter(context: Context) {
        presenter = PlayerHeroesPresenter(
            DependencyInjector.providePlayerHeroRepository(context)
        )
        presenter.onViewReady(this)
    }

    private fun initRv() {
        // Assign RV
        val rvPlayerHeroes = binding.playerHeroListLayout.rvPlayerHeroes

        //Init RecyclerView
        rvPlayerHeroesAdapter = PlayerHeroesListAdapter(playerHeroes, false)

        rvPlayerHeroes.layoutManager = LinearLayoutManager(this.context)
        rvPlayerHeroes.adapter = rvPlayerHeroesAdapter
    }

    override fun getPlayerHeroes(playerHeroes: List<PlayerHero>) {
        this.playerHeroes = playerHeroes
    }
}