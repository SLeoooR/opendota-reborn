package com.scottandmarc.opendotareborn.app.presentation.profile.heroes

import android.content.Context
import android.net.Network
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.scottandmarc.opendotareborn.app.domain.entities.PlayerHero
import com.scottandmarc.opendotareborn.databinding.FragmentPlayerHeroesBinding
import com.scottandmarc.opendotareborn.di.DependencyInjector
import com.scottandmarc.opendotareborn.toolbox.helpers.DialogHelper.Companion.createLoadingDialog
import com.scottandmarc.opendotareborn.toolbox.retrofit.NetworkConnectionChecker

class PlayerHeroesFragment : Fragment(), PlayerHeroesContract.View {

    private val binding: FragmentPlayerHeroesBinding by lazy {
        FragmentPlayerHeroesBinding.inflate(layoutInflater)
    }

    private lateinit var presenter: PlayerHeroesContract.Presenter
    private lateinit var rvPlayerHeroesAdapter: PlayerHeroesListAdapter
    private lateinit var playerHeroes: List<PlayerHero>

    private lateinit var loadingDialog: AlertDialog

    private var currentPage = 0
    private var totalPages = 0

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
    }

    private fun initPresenter(context: Context) {
        presenter = PlayerHeroesPresenter(
            DependencyInjector.provideCoroutineScopeProvider(),
            DependencyInjector.providePlayerRepository(context),
            DependencyInjector.providePlayerHeroRepository(),
            NetworkConnectionChecker(context)
        )
        presenter.onViewReady(this)
    }

    override fun setPlayerHeroes(playerHeroes: List<PlayerHero>) {
        this.playerHeroes = playerHeroes
    }

    override fun setTotalPages(totalPages: Int) {
        this.totalPages = totalPages
    }

    override fun getCurrentPage(): Int = currentPage

    override fun setCurrentPage(currentPage: Int) {
        this.currentPage = currentPage
    }

    override fun updateRv() {
        // Assign RV
        val rvPlayerHeroes = binding.playerHeroListLayout.rvPlayerHeroes

        //Init RecyclerView
        rvPlayerHeroesAdapter = PlayerHeroesListAdapter(playerHeroes, false)

        rvPlayerHeroes.layoutManager = LinearLayoutManager(this.context)
        rvPlayerHeroes.adapter = rvPlayerHeroesAdapter
    }

    override fun setupBtnNext() {
        binding.btnNext.setOnClickListener {
            Log.d("btnNext", currentPage.toString())
            currentPage++
            presenter.onNextBtnClick()

            val rvPlayerHeroes = binding.playerHeroListLayout.rvPlayerHeroes
            rvPlayerHeroes.adapter = PlayerHeroesListAdapter(playerHeroes, false)

            toggleButtons()
        }
    }

    override fun setupBtnPrev() {
        binding.btnPrev.setOnClickListener {
            Log.d("btnPrev", currentPage.toString())
            currentPage--
            presenter.onPrevBtnClick()

            val rvPlayerHeroes = binding.playerHeroListLayout.rvPlayerHeroes
            rvPlayerHeroes.adapter = PlayerHeroesListAdapter(playerHeroes, false)

            toggleButtons()
        }
    }

    override fun toggleButtons() {
        when (currentPage) {
            totalPages -> {
                binding.btnNext.visibility = View.INVISIBLE
                binding.btnNext.isEnabled = false

                binding.btnPrev.visibility = View.VISIBLE
                binding.btnPrev.isEnabled = true
            }
            0 -> {
                binding.btnNext.visibility = View.VISIBLE
                binding.btnNext.isEnabled = true

                binding.btnPrev.visibility = View.INVISIBLE
                binding.btnPrev.isEnabled = false
            }
            in 1..totalPages -> {
                binding.btnNext.visibility = View.VISIBLE
                binding.btnNext.isEnabled = true

                binding.btnPrev.visibility = View.VISIBLE
                binding.btnPrev.isEnabled = true
            }
        }
    }

    override fun showLoadingDialog() {
        loadingDialog = createLoadingDialog(requireContext(), layoutInflater)
        loadingDialog.show()
    }

    override fun dismissLoadingDialog() {
        loadingDialog.dismiss()
    }
}