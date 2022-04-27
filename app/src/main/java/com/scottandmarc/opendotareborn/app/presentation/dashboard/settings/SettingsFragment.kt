package com.scottandmarc.opendotareborn.app.presentation.dashboard.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.scottandmarc.opendotareborn.R
import com.scottandmarc.opendotareborn.databinding.FragmentSettingsBinding
import com.scottandmarc.opendotareborn.di.DependencyInjector
import com.scottandmarc.opendotareborn.toolbox.retrofit.NetworkConnectionChecker

class SettingsFragment : Fragment(), SettingsContract.View {

    private val binding: FragmentSettingsBinding by lazy {
        FragmentSettingsBinding.inflate(layoutInflater)
    }

    private lateinit var presenter: SettingsContract.Presenter

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
        binding.btnChangeSteamFriendCode.setOnClickListener {
            presenter.onChangeCodeBtnClick()
        }

        binding.llFAQOneTitle.setOnClickListener {
            if (binding.tvFAQOne.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(
                    binding.llFAQOne,
                    AutoTransition()
                )
                binding.tvFAQOne.visibility = View.VISIBLE
                binding.ivFAQOneIcon.setImageResource(R.drawable.ic_expand_less)
            } else {
                binding.tvFAQOne.visibility = View.GONE
                binding.ivFAQOneIcon.setImageResource(R.drawable.ic_expand_more)
            }
        }

        binding.llFAQTwoTitle.setOnClickListener {
            if (binding.tvFAQTwo.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(
                    binding.llFAQTwo,
                    AutoTransition()
                )
                binding.tvFAQTwo.visibility = View.VISIBLE
                binding.ivFAQTwoIcon.setImageResource(R.drawable.ic_expand_less)
            } else {
                binding.tvFAQTwo.visibility = View.GONE
                binding.ivFAQTwoIcon.setImageResource(R.drawable.ic_expand_more)
            }
        }

        binding.llFAQThreeTitle.setOnClickListener {
            if (binding.tvFAQThree.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(
                    binding.llFAQThree,
                    AutoTransition()
                )
                binding.tvFAQThree.visibility = View.VISIBLE
                binding.ivFAQThreeIcon.setImageResource(R.drawable.ic_expand_less)
            } else {
                binding.tvFAQThree.visibility = View.GONE
                binding.ivFAQThreeIcon.setImageResource(R.drawable.ic_expand_more)
            }
        }

        binding.llFAQFourTitle.setOnClickListener {
            if (binding.tvFAQFour.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(
                    binding.llFAQFour,
                    AutoTransition()
                )
                binding.tvFAQFour.visibility = View.VISIBLE
                binding.ivFAQFourIcon.setImageResource(R.drawable.ic_expand_less)
            } else {
                binding.tvFAQFour.visibility = View.GONE
                binding.ivFAQFourIcon.setImageResource(R.drawable.ic_expand_more)
            }
        }
    }

    private fun initPresenter() {
        presenter = SettingsPresenter(
            childFragmentManager,
            DependencyInjector.provideCoroutineScopeProvider(),
            DependencyInjector.providePlayerRepository(requireContext()),
            NetworkConnectionChecker(requireContext())
        )
        presenter.onViewReady(this)
    }

    private fun initToolbar() {
        val toolbar = activity?.findViewById<Toolbar>(R.id.tbUserDashboardView)
        toolbar?.title = "Settings"
        toolbar?.setTitleTextColor(ContextCompat.getColor(requireContext(), R.color.white))
    }
}