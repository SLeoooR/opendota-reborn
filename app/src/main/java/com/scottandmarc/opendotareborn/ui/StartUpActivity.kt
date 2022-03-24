package com.scottandmarc.opendotareborn.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.scottandmarc.opendotareborn.DependencyInjector
import com.scottandmarc.opendotareborn.databinding.ActivityStartUpBinding
import com.scottandmarc.opendotareborn.ui.getstarted.GetStartedActivity
import com.scottandmarc.opendotareborn.ui.profile.ProfileActivity
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.*

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
            val isPlayerLoggedIn = DependencyInjector.providePlayerRepository(applicationContext).count()
            // val isPlayerLoggedIn = 0
            if (isPlayerLoggedIn == 1) {
                startActivity(Intent(this@StartUpActivity, ProfileActivity::class.java))
            } else {
                startActivity(Intent(this@StartUpActivity, GetStartedActivity::class.java))
            }
        }
    }
}