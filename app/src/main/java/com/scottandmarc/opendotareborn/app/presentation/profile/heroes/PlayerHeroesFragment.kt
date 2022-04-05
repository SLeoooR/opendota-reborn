package com.scottandmarc.opendotareborn.app.presentation.profile.heroes

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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

    private var currentPage = 0

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
        setupBtnNext()
        setupBtnPrev()
        toggleButtons()
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

    override fun setPlayerHeroes(playerHeroes: List<PlayerHero>) {
        this.playerHeroes = playerHeroes
    }

    override fun getCurrentPage(): Int = currentPage

    override fun setCurrentPage(currentPage: Int) {
        this.currentPage = currentPage
    }

    override fun updateRv() {

    }

    private fun setupBtnNext() {
        binding.btnNext.setOnClickListener {
            Log.d("btnNext", currentPage.toString())
            currentPage++
            presenter.onNextBtnClick()

            val rvPlayerHeroes = binding.playerHeroListLayout.rvPlayerHeroes
            rvPlayerHeroes.adapter = PlayerHeroesListAdapter(playerHeroes, false)

            toggleButtons()
        }
    }

    private fun setupBtnPrev() {
        binding.btnPrev.setOnClickListener {
            Log.d("btnPrev", currentPage.toString())
            currentPage--
            presenter.onPrevBtnClick()

            val rvPlayerHeroes = binding.playerHeroListLayout.rvPlayerHeroes
            rvPlayerHeroes.adapter = PlayerHeroesListAdapter(playerHeroes, false)

            toggleButtons()
        }
    }

    private fun toggleButtons() {
        if (currentPage == presenter.getTotalPages()) {
            binding.btnNext.visibility = View.INVISIBLE
            binding.btnNext.isEnabled = false

            binding.btnPrev.visibility = View.VISIBLE
            binding.btnPrev.isEnabled = true
        } else if (currentPage == 0) {
            binding.btnNext.visibility = View.VISIBLE
            binding.btnNext.isEnabled = true

            binding.btnPrev.visibility = View.INVISIBLE
            binding.btnPrev.isEnabled = false
        } else if (currentPage >= 1 && currentPage <= presenter.getTotalPages()) {
            binding.btnNext.visibility = View.VISIBLE
            binding.btnNext.isEnabled = true

            binding.btnPrev.visibility = View.VISIBLE
            binding.btnPrev.isEnabled = true
        }
    }
}