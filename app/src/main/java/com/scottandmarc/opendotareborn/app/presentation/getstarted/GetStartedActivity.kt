package com.scottandmarc.opendotareborn.app.presentation.getstarted

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.scottandmarc.opendotareborn.databinding.ActivityGetStartedBinding
import com.scottandmarc.opendotareborn.app.presentation.login.LoginActivity

class GetStartedActivity : AppCompatActivity(), GetStartedContract.View {

    private val binding: ActivityGetStartedBinding by lazy {
        ActivityGetStartedBinding.inflate(layoutInflater)
    }

    private lateinit var presenter: GetStartedContract.Presenter
    private lateinit var btnGetStarted: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
        setupBtnGetStartedClickListener()

        initPresenter()
        presenter.onViewReady(this)
    }

    private fun initViews() {
        btnGetStarted = binding.btnGetStarted
    }

    private fun setupBtnGetStartedClickListener() {
        btnGetStarted.setOnClickListener {
            presenter.onGetStartedBtnClick()
        }
    }

    private fun initPresenter() {
        presenter = GetStartedPresenter()
    }

    override fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}