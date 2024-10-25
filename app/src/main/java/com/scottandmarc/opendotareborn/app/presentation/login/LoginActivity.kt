package com.scottandmarc.opendotareborn.app.presentation.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.net.toUri
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.scottandmarc.opendotareborn.R
import com.scottandmarc.opendotareborn.app.presentation.dashboard.UserDashboardActivity
import com.scottandmarc.opendotareborn.app.presentation.login.search.SearchActivity
import com.scottandmarc.opendotareborn.databinding.ActivityLoginBinding
import com.scottandmarc.opendotareborn.di.DependencyInjector
import com.scottandmarc.opendotareborn.toolbox.helpers.DialogHelper
import com.scottandmarc.opendotareborn.toolbox.retrofit.NetworkConnectionChecker

class LoginActivity : AppCompatActivity(), LoginContract.View {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private lateinit var presenter: LoginContract.Presenter
    private lateinit var btnContinue: AppCompatButton
    private lateinit var etAccountId: AppCompatEditText

    private lateinit var loadingDialog: AlertDialog

    private fun openSearchActivityForResult() {
        val intent = Intent(this@LoginActivity, SearchActivity::class.java)
        resultLauncher.launch(intent)
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            if (data?.hasExtra("accountId") == true) {
                val accountId = data.extras?.getInt("accountId")
                binding.etAccountId.setText(accountId.toString())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
        setupButtons()

        initPresenter()
        presenter.onViewReady(this)
    }

    private fun initViews() {
        btnContinue = binding.btnContinue
        etAccountId = binding.etAccountId
        btnContinue.alpha = .5f

        etAccountId.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    if (s.isNotEmpty()) {
                        btnContinue.isEnabled = true
                        btnContinue.alpha = 1F
                    } else {
                        btnContinue.isEnabled = false
                        btnContinue.alpha = .5F
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                }
            }
        )
    }

    private fun setupButtons() {
        btnContinue.setOnClickListener {
            presenter.onContinueBtnClick(etAccountId.text.toString().toInt())
        }

        binding.btnExpand.setOnClickListener {
            if (binding.llNeedHelp.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(
                    binding.llRoot,
                    AutoTransition()
                )
                binding.llNeedHelp.visibility = View.VISIBLE
                binding.btnExpand.setImageResource(R.drawable.ic_expand_less)
            } else {
                TransitionManager.beginDelayedTransition(
                    binding.llRoot,
                    AutoTransition()
                )
                binding.llNeedHelp.visibility = View.GONE
                binding.btnExpand.setImageResource(R.drawable.ic_expand_more)
            }
        }

        binding.btnLoginViaOpenDota.setOnClickListener {
            presenter.onLoginViaOpenDotaBtnClick()
        }

        binding.btnSearch.setOnClickListener {
            presenter.onSearchBtnClick()
        }
    }

    private fun initPresenter() {
        presenter = LoginPresenter(
            DependencyInjector.provideCoroutineScopeProvider(),
            DependencyInjector.providePlayerRepository(applicationContext),
            NetworkConnectionChecker(applicationContext)
        )
    }

    override fun navigateToProfile() {
        startActivity(Intent(this@LoginActivity, UserDashboardActivity::class.java))
    }

    override fun navigateToOpenDotaLogin() {
        startActivity(Intent(Intent.ACTION_VIEW, "https://api.opendota.com/login".toUri()))
    }

    override fun navigateToSearchPlayers() {
        openSearchActivityForResult()
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