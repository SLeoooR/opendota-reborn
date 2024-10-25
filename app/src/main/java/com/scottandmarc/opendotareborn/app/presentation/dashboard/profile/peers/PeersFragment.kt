package com.scottandmarc.opendotareborn.app.presentation.dashboard.profile.peers

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.scottandmarc.opendotareborn.app.domain.entities.Peer
import com.scottandmarc.opendotareborn.databinding.FragmentPeersBinding
import com.scottandmarc.opendotareborn.di.DependencyInjector
import com.scottandmarc.opendotareborn.toolbox.helpers.DialogHelper
import com.scottandmarc.opendotareborn.toolbox.retrofit.NetworkConnectionChecker

class PeersFragment(
    private val accountId: Int
) : Fragment(), PeersContract.View {

    private val binding: FragmentPeersBinding by lazy {
        FragmentPeersBinding.inflate(layoutInflater)
    }

    private lateinit var presenter: PeersContract.Presenter
    private lateinit var peers: List<Peer>
    private lateinit var peersListAdapter: PeersListAdapter

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

        initPresenter()
    }

    private fun initPresenter() {
        presenter = PeersPresenter(
            accountId,
            DependencyInjector.provideCoroutineScopeProvider(),
            DependencyInjector.providePlayerRepository(requireContext()),
            DependencyInjector.providePeerRepository(),
            NetworkConnectionChecker(requireContext())
        )
        presenter.onViewReady(this)
    }

    override fun setPeers(peers: List<Peer>) {
        this.peers = peers
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
        val rvPlayerHeroes = binding.peerListLayout.rvPeers

        //Init RecyclerView
        peersListAdapter = PeersListAdapter(peers)

        rvPlayerHeroes.layoutManager = LinearLayoutManager(this.context)
        rvPlayerHeroes.adapter = peersListAdapter
    }

    override fun setupBtnNext() {
        binding.btnNext.setOnClickListener {
            Log.d("btnNext", currentPage.toString())
            currentPage++
            presenter.onNextBtnClick()

            val rvPeers = binding.peerListLayout.rvPeers
            rvPeers.adapter = PeersListAdapter(peers)

            toggleButtons()
        }
    }

    override fun setupBtnPrev() {
        binding.btnPrev.setOnClickListener {
            Log.d("btnPrev", currentPage.toString())
            currentPage--
            presenter.onPrevBtnClick()

            val rvPeers = binding.peerListLayout.rvPeers
            rvPeers.adapter = PeersListAdapter(peers)

            toggleButtons()
        }
    }

    override fun toggleButtons() {
        if (totalPages == 0) {
            binding.btnNext.visibility = View.INVISIBLE
            binding.btnNext.isEnabled = false

            binding.btnPrev.visibility = View.INVISIBLE
            binding.btnPrev.isEnabled = false
        } else {
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
    }

    override fun showLoadingDialog() {
        binding.loadingLayout.visibility = View.VISIBLE
    }

    override fun dismissLoadingDialog() {
        binding.loadingLayout.visibility = View.INVISIBLE
    }

    override fun showOkayDialog(title: String, message: String, buttonText: String) {
        DialogHelper.createRetryDialog(
            context = requireContext(),
            title = title,
            message = message,
            buttonText = buttonText,
            buttonOnClick = { dialog, _ ->
                dialog.dismiss()
            }
        ).show()
    }
}