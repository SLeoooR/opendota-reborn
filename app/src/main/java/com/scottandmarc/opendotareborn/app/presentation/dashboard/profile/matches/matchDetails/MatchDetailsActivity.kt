package com.scottandmarc.opendotareborn.app.presentation.dashboard.profile.matches.matchDetails

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.scottandmarc.opendotareborn.R
import com.scottandmarc.opendotareborn.app.domain.entities.MatchPlayer
import com.scottandmarc.opendotareborn.databinding.ActivityMatchDetailsBinding
import com.scottandmarc.opendotareborn.di.DependencyInjector
import com.scottandmarc.opendotareborn.toolbox.helpers.DialogHelper
import com.scottandmarc.opendotareborn.toolbox.retrofit.NetworkConnectionChecker


class MatchDetailsActivity : AppCompatActivity(), MatchDetailsContract.View {

    private val binding: ActivityMatchDetailsBinding by lazy {
        ActivityMatchDetailsBinding.inflate(layoutInflater)
    }

    private lateinit var presenter: MatchDetailsContract.Presenter
    private lateinit var radiantPlayersListAdapter: MatchPlayersListAdapter
    private lateinit var direPlayersListAdapter: MatchPlayersListAdapter
    private lateinit var radiantPlayers: List<MatchPlayer>
    private lateinit var direPlayers: List<MatchPlayer>

    private var matchId: Long = 0
    private var title: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        matchId = intent.getLongExtra("matchId", 0)
        title = intent.getStringExtra("title")

        initViews()
        initPresenter()
        presenter.onViewReady(this)
    }

    private fun initViews() {
        // Start Toolbar Views
        binding.tbMatchDetails.title = title
        binding.tbMatchDetails.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        setSupportActionBar(binding.tbMatchDetails)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initPresenter() {
        presenter = MatchDetailsPresenter(
            matchId,
            DependencyInjector.provideMatchDetailsRepository(),
            DependencyInjector.provideCoroutineScopeProvider(),
            NetworkConnectionChecker(this)
        )
    }

    override fun showLoadingDialog() {
        binding.loadingLayout.visibility = View.VISIBLE
    }

    override fun dismissLoadingDialog() {
        binding.loadingLayout.visibility = View.INVISIBLE
    }

    override fun showMatchId(matchId: String) {
        binding.tvMatchId.text = matchId
        binding.ivCopyId.setOnClickListener {
            val clipboard: ClipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("matchId", matchId)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Copied match ID.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun showVictory(isRadiant: Boolean) {
        if (isRadiant) {
            binding.tvRadiantVictory.visibility = View.VISIBLE
        } else {
            binding.tvDireVictory.visibility = View.VISIBLE
        }
    }

    override fun showTeamKills(radiant: Int, dire: Int) {
        binding.tvRadiantKills.text = radiant.toString()
        binding.tvDireKills.text = dire.toString()
    }

    override fun showGeneralDetails(
        gameMode: String,
        duration: String,
        region: String,
        lastPlayed: String,
    ) {
        binding.tvGameMode.text = gameMode
        binding.tvDuration.text = duration
        binding.tvRegion.text = region
        binding.tvLastPlayed.text = lastPlayed
    }

    override fun setRadiantPlayersDetails(radiantPlayers: List<MatchPlayer>) {
        this.radiantPlayers = radiantPlayers
    }

    override fun setDirePlayersDetails(direPlayers: List<MatchPlayer>) {
        this.direPlayers = direPlayers
    }

    override fun updateRv() {
        // Assign RV
        val rvRadiantPlayers = binding.radiantPlayers.rvMatchPlayers
        val rvDirePlayers = binding.direPlayers.rvMatchPlayers

        //Init RecyclerView
        radiantPlayersListAdapter = MatchPlayersListAdapter(radiantPlayers)
        direPlayersListAdapter = MatchPlayersListAdapter(direPlayers)

        rvRadiantPlayers.layoutManager = LinearLayoutManager(this)
        rvRadiantPlayers.adapter = radiantPlayersListAdapter
        rvDirePlayers.layoutManager = LinearLayoutManager(this)
        rvDirePlayers.adapter = direPlayersListAdapter
    }

    override fun showOkayDialog(title: String, message: String, buttonText: String) {
        DialogHelper.createRetryDialog(
            context = this,
            title = title,
            message = message,
            buttonText = buttonText,
            buttonOnClick = { dialog, _ ->
                dialog.dismiss()
            }
        ).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }
}