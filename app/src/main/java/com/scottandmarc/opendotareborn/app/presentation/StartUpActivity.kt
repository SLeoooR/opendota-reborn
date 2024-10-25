package com.scottandmarc.opendotareborn.app.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.scottandmarc.opendotareborn.app.domain.entities.HeroInfo
import com.scottandmarc.opendotareborn.app.domain.gateways.HeroInfoGateway
import com.scottandmarc.opendotareborn.app.presentation.dashboard.UserDashboardActivity
import com.scottandmarc.opendotareborn.app.presentation.getstarted.GetStartedActivity
import com.scottandmarc.opendotareborn.databinding.ActivityStartUpBinding
import com.scottandmarc.opendotareborn.di.DependencyInjector
import com.scottandmarc.opendotareborn.toolbox.helpers.DialogHelper
import com.scottandmarc.opendotareborn.toolbox.retrofit.NetworkConnectionChecker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class StartUpActivity : AppCompatActivity(), CoroutineScope {
    private val binding: ActivityStartUpBinding by lazy {
        ActivityStartUpBinding.inflate(layoutInflater)
    }

    private lateinit var job: Job
    private lateinit var heroesInfo: List<HeroInfo>
    private lateinit var heroInfoGateway: HeroInfoGateway
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        job = Job()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        heroInfoGateway = DependencyInjector.provideHeroInfoRepository(applicationContext)
    }

    override fun onStart() {
        super.onStart()

        launch {
            delay(1000)
            try {
                startNavigation()
            } catch (e: Exception) {
                Log.d("error", e.localizedMessage?: "")
                DialogHelper.createRetryDialog(
                    context = this@StartUpActivity,
                    title = "Error",
                    message = e.localizedMessage ?: "",
                    buttonText = "Retry",
                    buttonOnClick = { _, _ -> this@StartUpActivity.recreate() }
                ).show()
            }
        }
    }

    private suspend fun startNavigation() {
        val playerRepository = DependencyInjector.providePlayerRepository(applicationContext)
        val isNetworkAvailable = NetworkConnectionChecker(applicationContext).isNetworkAvailable()

        if (isNetworkAvailable) {
            heroesInfo = heroInfoGateway.fetchHeroesInfo()
            heroesInfo.forEach {
                heroInfoGateway.insertHeroInfo(it)
            }
            Log.d("LocalHeroesInfo", heroInfoGateway.getHeroesInfo().toString())
        }

        val isPlayerLoggedIn = playerRepository.count()

        if (isPlayerLoggedIn == 1) {
            if (isNetworkAvailable) {
                playerRepository.insert(playerRepository.fetchPlayer(playerRepository.getPlayer().profile.accountId))
            }
            startActivity(Intent(this@StartUpActivity, UserDashboardActivity::class.java))
        } else {
            startActivity(Intent(this@StartUpActivity, GetStartedActivity::class.java))
        }
    }
}