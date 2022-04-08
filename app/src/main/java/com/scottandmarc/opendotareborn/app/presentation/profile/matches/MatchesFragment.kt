package com.scottandmarc.opendotareborn.app.presentation.profile.matches

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.scottandmarc.opendotareborn.app.domain.entities.Match
import com.scottandmarc.opendotareborn.databinding.FragmentMatchesBinding
import com.scottandmarc.opendotareborn.di.DependencyInjector
import com.scottandmarc.opendotareborn.toolbox.helpers.DialogHelper
import kotlinx.coroutines.launch

class MatchesFragment : Fragment(), MatchesContract.View {

    private val binding: FragmentMatchesBinding by lazy {
        FragmentMatchesBinding.inflate(layoutInflater)
    }

    private lateinit var presenter: MatchesContract.Presenter
    private lateinit var rvMatchesListAdapter: MatchesListAdapter
    private lateinit var matches: List<Match>

    private lateinit var loadingDialog: AlertDialog

    private var currentPage = 0
    private var totalPages = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPresenter(view.context)
    }

    private fun initPresenter(context: Context) {
        presenter = MatchesPresenter(
            DependencyInjector.provideCoroutineScopeProvider(),
            DependencyInjector.providePlayerRepository(context),
            DependencyInjector.provideMatchRepository()
        )
        presenter.onViewReady(this)
    }

    override fun setMatches(matches: List<Match>) {
        this.matches = matches
    }

    override fun setTotalPages(totalPages: Int) {
        this.totalPages = totalPages
    }

    override fun getCurrentPage(): Int = currentPage

    override fun setCurrentPage(currentPage: Int) {
        this.currentPage = currentPage
    }

    override fun updateRv() {
        // Assign RV
        val rvMatches = binding.matchListLayout.rvMatches

        //Init RecyclerView
        rvMatchesListAdapter = MatchesListAdapter(matches, false)

        rvMatches.layoutManager = LinearLayoutManager(this.context)
        rvMatches.adapter = rvMatchesListAdapter
    }

    override fun setupBtnNext() {
        binding.btnNext.setOnClickListener {
            Log.d("btnNext", currentPage.toString())
            currentPage++
            presenter.onNextBtnClick()

            val rvMatches = binding.matchListLayout.rvMatches
            rvMatches.adapter = MatchesListAdapter(matches, false)

            toggleButtons()
        }
    }

    override fun setupBtnPrev() {
        binding.btnPrev.setOnClickListener {
            Log.d("btnPrev", currentPage.toString())
            currentPage--
            presenter.onPrevBtnClick()

            val rvMatches = binding.matchListLayout.rvMatches
            rvMatches.adapter = MatchesListAdapter(matches, false)

            toggleButtons()
        }
    }

    override fun toggleButtons() {
        when (currentPage) {
            totalPages -> {
                binding.btnNext.visibility = View.INVISIBLE
                binding.btnNext.isEnabled = false

                binding.btnPrev.visibility = View.VISIBLE
                binding.btnPrev.isEnabled = true
            }
            0 -> {
                binding.btnNext.visibility = View.VISIBLE
                binding.btnNext.isEnabled = true

                binding.btnPrev.visibility = View.INVISIBLE
                binding.btnPrev.isEnabled = false
            }
            in 1..totalPages -> {
                binding.btnNext.visibility = View.VISIBLE
                binding.btnNext.isEnabled = true

                binding.btnPrev.visibility = View.VISIBLE
                binding.btnPrev.isEnabled = true
            }
        }
    }

    override fun showLoadingDialog() {
        loadingDialog = DialogHelper.createLoadingDialog(requireContext(), layoutInflater)
        loadingDialog.show()
    }

    override fun dismissLoadingDialog() {
        loadingDialog.dismiss()
    }
}