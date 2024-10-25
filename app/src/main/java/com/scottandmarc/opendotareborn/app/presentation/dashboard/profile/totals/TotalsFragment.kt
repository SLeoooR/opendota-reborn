package com.scottandmarc.opendotareborn.app.presentation.dashboard.profile.totals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.scottandmarc.opendotareborn.app.domain.entities.Total
import com.scottandmarc.opendotareborn.databinding.FragmentTotalsBinding
import com.scottandmarc.opendotareborn.di.DependencyInjector
import com.scottandmarc.opendotareborn.toolbox.helpers.DialogHelper
import com.scottandmarc.opendotareborn.toolbox.retrofit.NetworkConnectionChecker

class TotalsFragment(
    private val accountId: Int
) : Fragment(), TotalContract.View {

    private val binding: FragmentTotalsBinding by lazy {
        FragmentTotalsBinding.inflate(layoutInflater)
    }

    private lateinit var presenter: TotalContract.Presenter
    private lateinit var totals: List<Total>
    private lateinit var totalListAdapter: TotalListAdapter

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
        presenter = TotalPresenter(
            accountId,
            DependencyInjector.provideCoroutineScopeProvider(),
            DependencyInjector.providePlayerRepository(requireContext()),
            DependencyInjector.provideTotalRepository(),
            NetworkConnectionChecker(requireContext())
        )
        presenter.onViewReady(this)
    }

    override fun setTotals(totals: List<Total>) {
        this.totals = totals
    }

    override fun updateRv() {
        // Assign RV
        val rvPlayerHeroes = binding.rvPeers

        //Init RecyclerView
        totalListAdapter = TotalListAdapter(totals)

        rvPlayerHeroes.layoutManager = GridLayoutManager(this.context, 2)
        rvPlayerHeroes.adapter = totalListAdapter
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