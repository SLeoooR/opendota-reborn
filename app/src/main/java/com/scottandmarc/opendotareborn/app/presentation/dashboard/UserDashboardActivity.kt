package com.scottandmarc.opendotareborn.app.presentation.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.scottandmarc.opendotareborn.R
import com.scottandmarc.opendotareborn.app.presentation.dashboard.heroes.HeroesFragment
import com.scottandmarc.opendotareborn.app.presentation.dashboard.profile.ProfileFragment
import com.scottandmarc.opendotareborn.app.presentation.dashboard.search.SearchFragment
import com.scottandmarc.opendotareborn.app.presentation.dashboard.settings.SettingsFragment
import com.scottandmarc.opendotareborn.app.presentation.dashboard.teams.TeamsFragment
import com.scottandmarc.opendotareborn.databinding.DrawerNavViewBinding

class UserDashboardActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {

    private val bindingNavView: DrawerNavViewBinding by lazy {
        DrawerNavViewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindingNavView.root)

        initViews()
    }

    private fun initViews() {
        // Start Toolbar Views
        setSupportActionBar(bindingNavView.activityUserDashboard.tbUserDashboardView)
        supportActionBar?.title = ""

        val toggle = ActionBarDrawerToggle(this, bindingNavView.drawerLayout, bindingNavView.activityUserDashboard.tbUserDashboardView, R.string.nav_open, R.string.nav_close)
        toggle.drawerArrowDrawable.color = ContextCompat.getColor(this, R.color.white)
        bindingNavView.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        bindingNavView.nvLayout.setNavigationItemSelectedListener(this)
        // End Toolbar Views

        loadFragment(ProfileFragment())
    }

    private fun loadFragment(fragment: Fragment) {
        // Show Overview Fragment
        val contentFrameId = bindingNavView.activityUserDashboard.contentFrameUserDashboard.id
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(contentFrameId, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                loadFragment(ProfileFragment())
            }
            R.id.nav_search_player -> {
                loadFragment(SearchFragment())
            }
            R.id.nav_heroes -> {
                loadFragment(HeroesFragment())
            }
            R.id.nav_teams -> {
                loadFragment(TeamsFragment())
            }
            R.id.nav_settings -> {
                loadFragment(SettingsFragment())
            }
        }
        bindingNavView.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}