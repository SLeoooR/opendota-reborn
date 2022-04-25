package com.scottandmarc.opendotareborn.app.presentation.dashboard.heroes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.scottandmarc.opendotareborn.R
import com.scottandmarc.opendotareborn.app.domain.entities.HeroStats
import com.scottandmarc.opendotareborn.app.presentation.dashboard.heroes.pro.ProFragment
import com.scottandmarc.opendotareborn.app.presentation.dashboard.heroes.pub.PubFragment
import com.scottandmarc.opendotareborn.app.presentation.dashboard.heroes.turbo.TurboFragment
import com.scottandmarc.opendotareborn.databinding.FragmentHeroesBinding
import com.scottandmarc.opendotareborn.di.DependencyInjector
import com.scottandmarc.opendotareborn.toolbox.helpers.DialogHelper
import com.scottandmarc.opendotareborn.toolbox.retrofit.NetworkConnectionChecker

class HeroesFragment : Fragment(), HeroesContract.View {

    private val binding: FragmentHeroesBinding by lazy {
        FragmentHeroesBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    private lateinit var presenter: HeroesContract.Presenter
    private lateinit var heroesStats: List<HeroStats>
    private lateinit var loadingDialog: AlertDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initToolbar()
        initPresenter()
    }

    private fun initPresenter() {
        presenter = HeroesPresenter(
            DependencyInjector.provideCoroutineScopeProvider(),
            DependencyInjector.provideHeroStatsRepository(),
            NetworkConnectionChecker(requireContext())
        )
        presenter.onViewReady(this)
    }

    private fun initToolbar() {
        val toolbar = activity?.findViewById<Toolbar>(R.id.tbUserDashboardView)
        toolbar?.title = "Heroes"
        toolbar?.setTitleTextColor(ContextCompat.getColor(requireContext(), R.color.white))
    }

    private fun initViews() {
        // Start BottomNavView
        val bottomNav = binding.bottomNav
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_bottom_pro -> {
                    //showSteamIcon()
                    loadFragment(ProFragment(heroesStats))
                }
                R.id.nav_bottom_pub -> {
                    loadFragment(PubFragment(heroesStats))
                    //hideSteamIcon()
                }
                R.id.nav_bottom_turbo -> {
                    loadFragment(TurboFragment(heroesStats))
                    //hideSteamIcon()
                }
            }
            true
        }
        // End Bottom NavView
    }

    private fun loadFragment(fragment: Fragment) {
        // Show Overview Fragment
        val contentFrameId = binding.contentFrameHeroes.id
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(contentFrameId, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun setHeroesStats(heroesStats: List<HeroStats>) {
        this.heroesStats = heroesStats

        loadFragment(ProFragment(this.heroesStats))
    }

    override fun showLoadingDialog() {
        loadingDialog = DialogHelper.createLoadingDialog(requireContext(), layoutInflater)
        loadingDialog.show()
    }

    override fun dismissLoadingDialog() {
        loadingDialog.dismiss()
    }
}