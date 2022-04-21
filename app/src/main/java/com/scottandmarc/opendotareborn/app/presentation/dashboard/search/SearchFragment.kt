package com.scottandmarc.opendotareborn.app.presentation.dashboard.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.scottandmarc.opendotareborn.R
import com.scottandmarc.opendotareborn.app.domain.entities.Search
import com.scottandmarc.opendotareborn.databinding.FragmentSearchBinding
import com.scottandmarc.opendotareborn.di.DependencyInjector
import com.scottandmarc.opendotareborn.toolbox.helpers.DialogHelper
import com.scottandmarc.opendotareborn.toolbox.retrofit.NetworkConnectionChecker

class SearchFragment : Fragment(), SearchContract.View {

    private val binding: FragmentSearchBinding by lazy {
        FragmentSearchBinding.inflate(layoutInflater)
    }

    private lateinit var presenter: SearchContract.Presenter
    private lateinit var searches: List<Search>
    private lateinit var loadingDialog: AlertDialog
    private lateinit var searchListAdapter: SearchListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            NetworkConnectionChecker(requireContext())
        )
        presenter.onViewReady(this)
    }

    private fun initToolbar() {
        val toolbar = activity?.findViewById<Toolbar>(R.id.tbUserDashboardView)
        toolbar?.title = "Search Player"
        toolbar?.setTitleTextColor(ContextCompat.getColor(requireContext(), R.color.white))
    }

    override fun showLoadingDialog() {
        loadingDialog = DialogHelper.createLoadingDialog(requireContext(), layoutInflater)
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

        rvSearches.layoutManager = LinearLayoutManager(this.context)
        rvSearches.adapter = searchListAdapter
    }
}