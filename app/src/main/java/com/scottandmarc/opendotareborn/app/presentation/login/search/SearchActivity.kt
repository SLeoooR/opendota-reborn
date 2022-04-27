package com.scottandmarc.opendotareborn.app.presentation.login.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.scottandmarc.opendotareborn.R
import com.scottandmarc.opendotareborn.app.domain.entities.Search
import com.scottandmarc.opendotareborn.databinding.ActivitySearchBinding
import com.scottandmarc.opendotareborn.di.DependencyInjector
import com.scottandmarc.opendotareborn.toolbox.helpers.DialogHelper
import com.scottandmarc.opendotareborn.toolbox.retrofit.NetworkConnectionChecker

class SearchActivity : AppCompatActivity(), SearchContract.View {

    private val binding: ActivitySearchBinding by lazy {
        ActivitySearchBinding.inflate(layoutInflater)
    }

    private lateinit var presenter: SearchContract.Presenter
    private lateinit var searches: List<Search>
    private lateinit var loadingDialog: AlertDialog
    private lateinit var searchListAdapter: SearchListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initToolbar()
        initPresenter()
        initViews()
    }

    private fun initViews() {
        binding.btnSearch.isEnabled = false
        binding.btnSearch.alpha = .5f

        binding.etSearchPlayerQuery.addTextChangedListener(
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
                        binding.btnSearch.isEnabled = true
                        binding.btnSearch.alpha = 1F
                    } else {
                        binding.btnSearch.isEnabled = false
                        binding.btnSearch.alpha = .5F
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                }
            }
        )

        binding.btnSearch.setOnClickListener{
            val query = binding.etSearchPlayerQuery.text.toString()
            presenter.onBtnSearchClicked(query)
            binding.etSearchPlayerQuery.onEditorAction(EditorInfo.IME_ACTION_DONE)
        }
    }

    private fun initPresenter() {
        presenter = SearchPresenter(
            DependencyInjector.provideCoroutineScopeProvider(),
            DependencyInjector.provideSearchRepository(),
            NetworkConnectionChecker(this)
        )
        presenter.onViewReady(this)
    }

    private fun initToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.tbSearchPlayer)
        toolbar?.title = "Search Player"
        toolbar?.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun showLoadingDialog() {
        loadingDialog = DialogHelper.createLoadingDialog(this, layoutInflater)
        loadingDialog.show()
    }

    override fun dismissLoadingDialog() {
        loadingDialog.dismiss()
    }

    override fun setSearches(searches: List<Search>) {
        this.searches = searches
    }

    override fun updateRv() {
        // Assign RV
        val rvSearches = binding.rvSearches

        //Init RecyclerView
        searchListAdapter = SearchListAdapter(searches)

        rvSearches.layoutManager = LinearLayoutManager(this)
        rvSearches.adapter = searchListAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }
}