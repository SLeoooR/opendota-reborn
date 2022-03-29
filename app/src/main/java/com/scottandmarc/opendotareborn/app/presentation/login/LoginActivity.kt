package com.scottandmarc.opendotareborn.app.presentation.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.scottandmarc.opendotareborn.app.presentation.profile.ProfileActivity
import com.scottandmarc.opendotareborn.databinding.ActivityLoginBinding
import com.scottandmarc.opendotareborn.di.DependencyInjector
import com.scottandmarc.opendotareborn.toolbox.helpers.DialogHelper

class LoginActivity : AppCompatActivity(), LoginContract.View {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private lateinit var presenter: LoginContract.Presenter
    private lateinit var btnContinue: AppCompatButton
    private lateinit var etAccountId: AppCompatEditText
    private lateinit var tvSteamFriendCode: AppCompatTextView

    private lateinit var loadingDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
        setupBtnContinueClickListener()

        initPresenter()
        presenter.onViewReady(this)
    }

    private fun initViews() {
        btnContinue = binding.btnContinue
        etAccountId = binding.etAccountId
        tvSteamFriendCode = binding.tvSteamFriendCode
    }

    @SuppressLint("SetTextI18n")
    private fun setupBtnContinueClickListener() {
        btnContinue.setOnClickListener {
            println(etAccountId.text.toString().toInt())
            presenter.onContinueBtnClick(etAccountId.text.toString().toInt())
        }

        tvSteamFriendCode.setOnClickListener{
            etAccountId.setText("131405121")
        }
    }

    private fun initPresenter() {
        presenter = LoginPresenter(
            DependencyInjector.providePlayerRepository(applicationContext),
            DependencyInjector.provideCoroutineScopeProvider()
        )
    }

    override fun navigateToProfile() {
        startActivity(Intent(this@LoginActivity, ProfileActivity::class.java))
    }

    override fun showLoadingDialog() {
        loadingDialog = DialogHelper.createLoadingDialog(this, layoutInflater)
        loadingDialog.show()
    }

    override fun dismissLoadingDialog() {
        loadingDialog.dismiss()
    }

    override fun displayToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}