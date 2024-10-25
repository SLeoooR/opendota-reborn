package com.scottandmarc.opendotareborn.app.presentation.dashboard.profile.overview

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.scottandmarc.opendotareborn.R
import com.scottandmarc.opendotareborn.app.domain.entities.ProcessedRecentMatch
import com.scottandmarc.opendotareborn.databinding.FragmentOverviewBinding
import com.scottandmarc.opendotareborn.di.DependencyInjector
import com.scottandmarc.opendotareborn.toolbox.helpers.DialogHelper
import com.scottandmarc.opendotareborn.toolbox.retrofit.NetworkConnectionChecker
import com.squareup.picasso.Picasso

class OverviewFragment(
    private val accountId: Int
) : Fragment(), OverviewContract.View {

    private val binding: FragmentOverviewBinding by lazy {
        FragmentOverviewBinding.inflate(layoutInflater)
    }

    private lateinit var presenter: OverviewContract.Presenter
    private lateinit var processedRecentMatches: List<ProcessedRecentMatch>
    private lateinit var recentMatchesListAdapter: RecentMatchesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPresenter(view.context)
    }

    private fun initPresenter(context: Context) {
        presenter = OverviewPresenter(
            accountId,
            DependencyInjector.provideCoroutineScopeProvider(),
            DependencyInjector.providePlayerRepository(context),
            DependencyInjector.provideRecentMatchRepository(),
            NetworkConnectionChecker(context)
        )
        presenter.onViewReady(this)
    }

    override fun showProfilePic(profilePicURL: String) {
        val circularProgressDrawable = CircularProgressDrawable(requireContext()).apply {
            strokeWidth = 5F
            centerRadius = 15F
            setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.white))
            start()
        }
        Picasso.get().load(profilePicURL).error(R.drawable.ic_question_mark).placeholder(circularProgressDrawable).into(binding.ivPlayerProfilePic)
    }

    override fun showPlayerRankPic(rankPicURL: String, starsPicURL: String) {
        Picasso.get().load(rankPicURL).into(binding.ivPlayerRankIcon)
        Picasso.get().load(starsPicURL).into(binding.ivPlayerStarIcon)
    }

    override fun showPlayerName(playerName: String) {
        binding.tvPlayerName.text = playerName
    }

    override fun showPlayerWins(wins: Int) {
        binding.tvPlayerWins.text = wins.toString()
    }

    override fun showPlayerLosses(losses: Int) {
        binding.tvPlayerLosses.text = losses.toString()
    }

    override fun showPlayerWinRate(winRate: Float) {
        val text = "${String.format("%.2f", winRate)}%"
        binding.tvPlayerWinRate.text = text
    }

    override fun showLoadingDialog() {
        binding.loadingLayout.visibility = View.VISIBLE
    }

    override fun dismissLoadingDialog() {
        binding.loadingLayout.visibility = View.INVISIBLE
    }

    override fun setProcessedRecentMatches(processedRecentMatches: MutableList<ProcessedRecentMatch>) {
        this.processedRecentMatches = processedRecentMatches
    }

    override fun updateRv() {
        // Assign RV
        val rvPlayerHeroes = binding.recentMatchesLayout.rvRecentMatches

        //Init RecyclerView
        recentMatchesListAdapter = RecentMatchesListAdapter(processedRecentMatches)

        rvPlayerHeroes.layoutManager = LinearLayoutManager(this.context)
        rvPlayerHeroes.adapter = recentMatchesListAdapter
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