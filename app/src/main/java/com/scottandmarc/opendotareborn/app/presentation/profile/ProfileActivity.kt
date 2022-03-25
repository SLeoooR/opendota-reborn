package com.scottandmarc.opendotareborn.app.presentation.profile

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.scottandmarc.opendotareborn.R
import com.scottandmarc.opendotareborn.databinding.DrawerNavViewBinding

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
        print(bindingNavView.drawerLayout)
        setSupportActionBar(bindingNavView.activityProfile.tbProfileView)
        supportActionBar?.title = ""

        val toggle = ActionBarDrawerToggle(this, bindingNavView.drawerLayout, bindingNavView.activityProfile.tbProfileView, R.string.nav_open, R.string.nav_close)
        toggle.drawerArrowDrawable.color = ContextCompat.getColor(this, R.color.white)
        bindingNavView.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        bindingNavView.nvLayout.setNavigationItemSelectedListener(this)
    }

    private fun initPresenter() {
        presenter = ProfilePresenter()
    }

    override fun displayToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
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
}