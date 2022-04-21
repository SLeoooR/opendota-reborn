package com.scottandmarc.opendotareborn.app.presentation.dashboard.search

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.scottandmarc.opendotareborn.R
import com.scottandmarc.opendotareborn.app.presentation.dashboard.profile.ProfileFragment
import com.scottandmarc.opendotareborn.databinding.ActivitySearchedPlayerBinding

class SearchedPlayerActivity : AppCompatActivity() {
    private val binding: ActivitySearchedPlayerBinding by lazy {
        ActivitySearchedPlayerBinding.inflate(layoutInflater)
    }

    private var accountId: Int = 0
    private var title: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        accountId = intent.getIntExtra("accountId", 0)
        title = intent.getStringExtra("title")

        initViews()
    }

    private fun initViews() {
        // Start Toolbar Views
        binding.tbSearchedPlayerView.title = title
        binding.tbSearchedPlayerView.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        setSupportActionBar(binding.tbSearchedPlayerView)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // End Toolbar Views

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(binding.contentFrameSearchedPlayer.id, ProfileFragment(accountId))
        transaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }
}