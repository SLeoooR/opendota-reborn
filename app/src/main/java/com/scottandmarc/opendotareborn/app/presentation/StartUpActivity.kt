package com.scottandmarc.opendotareborn.app.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.scottandmarc.opendotareborn.app.presentation.dashboard.UserDashboardActivity
import com.scottandmarc.opendotareborn.app.presentation.getstarted.GetStartedActivity
import com.scottandmarc.opendotareborn.databinding.ActivityStartUpBinding
import com.scottandmarc.opendotareborn.di.DependencyInjector
import com.scottandmarc.opendotareborn.toolbox.retrofit.NetworkConnectionChecker
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class StartUpActivity : AppCompatActivity(), CoroutineScope {
    private val binding: ActivityStartUpBinding by lazy {
        ActivityStartUpBinding.inflate(layoutInflater)
    }

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        job = Job()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        launch {
            delay(1000)
            val playerRepository = DependencyInjector.providePlayerRepository(applicationContext)
            val isNetworkAvailable = NetworkConnectionChecker(applicationContext).isNetworkAvailable()

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
}