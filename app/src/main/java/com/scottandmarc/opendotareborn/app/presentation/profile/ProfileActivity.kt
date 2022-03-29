package com.scottandmarc.opendotareborn.app.presentation.profile

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import com.scottandmarc.opendotareborn.R
import com.scottandmarc.opendotareborn.app.presentation.profile.overview.OverviewFragment
import com.scottandmarc.opendotareborn.databinding.DrawerNavViewBinding
import com.scottandmarc.opendotareborn.di.DependencyInjector

class ProfileActivity : AppCompatActivity(), ProfileContract.View,
    NavigationView.OnNavigationItemSelectedListener {

    private val bindingNavView: DrawerNavViewBinding by lazy {
        DrawerNavViewBinding.inflate(layoutInflater)
    }

    private lateinit var presenter: ProfileContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindingNavView.root)

        initViews()
        initPresenter()
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

        // Show Overview Fragment
        val contentFrameId = bindingNavView.activityProfile.contentFrameProfile.id
        var fragment: Fragment = OverviewFragment()
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.add(contentFrameId, fragment)
        ft.commit()

        // Start BottomNavView
        val bottomNav = bindingNavView.activityProfile.bottomNav
        bottomNav.setOnItemSelectedListener {
            val ftBottomNav: FragmentTransaction = supportFragmentManager.beginTransaction()

            when (it.itemId) {
                R.id.nav_bottom_overview -> {
                    fragment = OverviewFragment()
                }
                R.id.nav_bottom_matches -> {
                    displayToast("Matches clicked.")
                }
                R.id.nav_bottom_heroes -> {
                    displayToast("Heroes clicked.")
                }
                R.id.nav_bottom_peers -> {
                    displayToast("Peers clicked.")
                }
                R.id.nav_bottom_totals -> {
                    displayToast("Totals clicked.")
                }
            }
            ftBottomNav.replace(contentFrameId, fragment)
            ftBottomNav.commit()
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

    private fun initPresenter() {
        presenter = ProfilePresenter(
            DependencyInjector.providePlayerRepository(applicationContext)
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
        menuInflater.inflate(R.menu.steam_menu, menu)
        return true
    }
}