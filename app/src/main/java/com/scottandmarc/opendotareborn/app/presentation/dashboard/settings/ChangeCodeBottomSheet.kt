package com.scottandmarc.opendotareborn.app.presentation.dashboard.settings

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.scottandmarc.opendotareborn.app.data.player.PlayerRepository
import com.scottandmarc.opendotareborn.app.domain.entities.Player
import com.scottandmarc.opendotareborn.app.presentation.dashboard.UserDashboardActivity
import com.scottandmarc.opendotareborn.databinding.FragmentChangeCodeBottomSheetBinding
import com.scottandmarc.opendotareborn.toolbox.helpers.CoroutineScopeProvider
import com.scottandmarc.opendotareborn.toolbox.helpers.DialogHelper
import com.scottandmarc.opendotareborn.toolbox.retrofit.NetworkConnectionChecker
import kotlinx.coroutines.launch

class ChangeCodeBottomSheet(
    private val coroutineScopeProvider: CoroutineScopeProvider,
    private val playerRepository: PlayerRepository,
    private val networkConnectionChecker: NetworkConnectionChecker
) : BottomSheetDialogFragment() {
    private var binding: FragmentChangeCodeBottomSheetBinding? = null

    private lateinit var playerData: Player

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChangeCodeBottomSheetBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loadingDialog = DialogHelper.createLoadingDialog(requireContext(), layoutInflater)

        binding!!.btnUpdate.alpha = .5F

        binding?.etAccountId?.addTextChangedListener(
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
                        binding!!.btnUpdate.isEnabled = true
                        binding!!.btnUpdate.alpha = 1F
                    } else {
                        binding!!.btnUpdate.isEnabled = false
                        binding!!.btnUpdate.alpha = .5F
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                }
            }
        )

        binding?.btnUpdate?.setOnClickListener {
            val accountId = binding?.etAccountId?.text.toString()

            coroutineScopeProvider.provide().launch {
                try {
                    loadingDialog.show()

                    if (networkConnectionChecker.isNetworkAvailable()) {
                        playerData = playerRepository.fetchPlayer(accountId.toInt())

                        val playerNotExist =
                            playerData.trackedUntil == null &&
                                    playerData.soloCompetitiveRank == null &&
                                    playerData.leaderboardRank == null &&
                                    playerData.competitiveRank == null &&
                                    playerData.rankTier == null &&
                                    playerData.mmrEstimate.estimate == null

                        if (playerNotExist) {
                            Toast.makeText(requireContext(), "Player does not exist", Toast.LENGTH_SHORT).show()
                        } else {
                            playerRepository.delete()
                            playerRepository.insert(playerData)

                            startActivity(Intent(requireContext(), UserDashboardActivity::class.java))
                        }
                    } else {
                        Toast.makeText(requireContext(), "No internet connection", Toast.LENGTH_SHORT).show()
                    }

                    loadingDialog.dismiss()
                } catch (e: Exception) {
                    loadingDialog.dismiss()
                    Toast.makeText(requireContext(), e.localizedMessage?: "", Toast.LENGTH_SHORT).show()
                    Log.d("error", e.localizedMessage?: "")
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding?.etAccountId?.setText("")
        binding = null
    }
}