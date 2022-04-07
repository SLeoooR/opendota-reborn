package com.scottandmarc.opendotareborn.app.presentation.profile

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.scottandmarc.opendotareborn.R
import com.scottandmarc.opendotareborn.app.presentation.profile.heroes.PlayerHeroesFragment
import com.scottandmarc.opendotareborn.app.presentation.profile.matches.MatchesFragment
import com.scottandmarc.opendotareborn.app.presentation.profile.overview.OverviewFragment
import com.scottandmarc.opendotareborn.app.presentation.profile.peers.PeersFragment
import com.scottandmarc.opendotareborn.app.presentation.profile.totals.TotalsFragment
import com.scottandmarc.opendotareborn.databinding.DrawerNavViewBinding
import com.scottandmarc.opendotareborn.di.DependencyInjector
import com.scottandmarc.opendotareborn.toolbox.helpers.DialogHelper
import com.scottandmarc.opendotareborn.toolbox.helpers.LoadingFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ProfileActivity : AppCompatActivity(), ProfileContract.View,
    NavigationView.OnNavigationItemSelectedListener {

    private val bindingNavView: DrawerNavViewBinding by lazy {
        DrawerNavViewBinding.inflate(layoutInflater)
    }

    private lateinit var presenter: ProfileContract.Presenter
    private lateinit var menuList: Menu
    private lateinit var loadingDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindingNavView.root)

        initPresenter()
        initViews()
    }

    private fun initViews() {
        // Start Toolbar Views
        setSupportActionBar(bindingNavView.activityProfile.tbProfileView)
        supportActionBar?.title = ""

        val toggle = ActionBarDrawerToggle(this, bindingNavView.drawerLayout, bindingNavView.activityProfile.tbProfileView, R.string.nav_open, R.string.nav_close)
        toggle.drawerArrowDrawable.color = ContextCompat.getColor(this, R.color.white)
        bindingNavView.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        bindingNavView.nvLayout.setNavigationItemSelectedListener(this)
        // End Toolbar Views

        loadFragment(OverviewFragment())

        // Start BottomNavView
        val bottomNav = bindingNavView.activityProfile.bottomNav
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_bottom_overview -> {
                    showSteamIcon()
                    loadFragment(OverviewFragment())
                }
                R.id.nav_bottom_matches -> {
                    loadFragment(MatchesFragment())
                    hideSteamIcon()
                }
                R.id.nav_bottom_heroes -> {
                    loadFragment(PlayerHeroesFragment())
                    hideSteamIcon()
                }
                R.id.nav_bottom_peers -> {
                    loadFragment(PeersFragment())
                    hideSteamIcon()
                }
                R.id.nav_bottom_totals -> {
                    loadFragment(TotalsFragment())
                    hideSteamIcon()
                }
            }
            true
        }
        // End Bottom NavView

        bindingNavView.activityProfile.tbProfileView.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.steam_profile -> {
                    presenter.onBtnSteamIconClick()
                }
            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        // Show Overview Fragment
        val contentFrameId = bindingNavView.activityProfile.contentFrameProfile.id
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(contentFrameId, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun initPresenter() {
        presenter = ProfilePresenter(
            DependencyInjector.provideCoroutineScopeProvider(),
            DependencyInjector.providePlayerRepository(applicationContext),
            DependencyInjector.providePlayerHeroRepository(applicationContext),
            DependencyInjector.provideMatchRepository(applicationContext)
        )
        presenter.onViewReady(this)
    }

    override fun displayToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun navigateToSteamProfile(profileURL: String) {
        startActivity(Intent(Intent.ACTION_VIEW, profileURL.toUri()))
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                displayToast("Profile clicked.")
            }
            R.id.nav_search_player -> {
                displayToast("Search Player clicked.")
            }
            R.id.nav_heroes -> {
                displayToast("Heroes clicked.")
            }
            R.id.nav_teams -> {
                displayToast("Teams clicked.")
            }
            R.id.nav_settings -> {
                displayToast("Settings clicked.")
            }
        }
        bindingNavView.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuList = menu
        menuInflater.inflate(R.menu.steam_menu, menu)
        return true
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
        loadingDialog = DialogHelper.createLoadingDialog(this, layoutInflater)
        loadingDialog.show()
    }

    override fun dismissLoadingDialog() {
        loadingDialog.dismiss()
    }
}