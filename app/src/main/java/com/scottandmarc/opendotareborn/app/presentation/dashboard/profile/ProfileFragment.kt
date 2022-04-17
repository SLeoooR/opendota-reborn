package com.scottandmarc.opendotareborn.app.presentation.dashboard.profile

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.scottandmarc.opendotareborn.R
import com.scottandmarc.opendotareborn.app.presentation.dashboard.profile.heroes.PlayerHeroesFragment
import com.scottandmarc.opendotareborn.app.presentation.dashboard.profile.matches.MatchesFragment
import com.scottandmarc.opendotareborn.app.presentation.dashboard.profile.overview.OverviewFragment
import com.scottandmarc.opendotareborn.app.presentation.dashboard.profile.peers.PeersFragment
import com.scottandmarc.opendotareborn.app.presentation.dashboard.profile.totals.TotalsFragment
import com.scottandmarc.opendotareborn.databinding.FragmentProfileBinding
import com.scottandmarc.opendotareborn.di.DependencyInjector
import com.scottandmarc.opendotareborn.toolbox.helpers.DialogHelper

class ProfileFragment : Fragment(), ProfileContract.View {

    private val binding: FragmentProfileBinding by lazy {
        FragmentProfileBinding.inflate(layoutInflater)
    }

    private lateinit var presenter: ProfileContract.Presenter
    private lateinit var menuList: Menu
    private lateinit var loadingDialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPresenter()
        initViews()
    }

    private fun initViews() {
        loadFragment(OverviewFragment())

        // Start BottomNavView
        val bottomNav = binding.bottomNav
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_bottom_overview -> {
                    //showSteamIcon()
                    loadFragment(OverviewFragment())
                }
                R.id.nav_bottom_matches -> {
                    loadFragment(MatchesFragment())
                    //hideSteamIcon()
                }
                R.id.nav_bottom_heroes -> {
                    loadFragment(PlayerHeroesFragment())
                    //hideSteamIcon()
                }
                R.id.nav_bottom_peers -> {
                    loadFragment(PeersFragment())
                    //hideSteamIcon()
                }
                R.id.nav_bottom_totals -> {
                    loadFragment(TotalsFragment())
                    //hideSteamIcon()
                }
            }
            true
        }
        // End Bottom NavView
    }

    private fun loadFragment(fragment: Fragment) {
        // Show Overview Fragment
        val contentFrameId = binding.contentFrameProfile.id
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(contentFrameId, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun initPresenter() {
        presenter = ProfilePresenter(
            DependencyInjector.providePlayerRepository(requireContext()),
        )
        presenter.onViewReady(this)
    }

    override fun displayToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    override fun navigateToSteamProfile(profileURL: String) {
        startActivity(Intent(Intent.ACTION_VIEW, profileURL.toUri()))
    }

    private fun hideSteamIcon() {
        val item: MenuItem = menuList.findItem(R.id.steam_profile)
        item.isVisible = false
    }

    private fun showSteamIcon() {
        val item: MenuItem = menuList.findItem(R.id.steam_profile)
        item.isVisible = true
    }

    override fun showLoadingDialog() {
        loadingDialog = DialogHelper.createLoadingDialog(requireContext(), layoutInflater)
        loadingDialog.show()
    }

    override fun dismissLoadingDialog() {
        loadingDialog.dismiss()
    }
}