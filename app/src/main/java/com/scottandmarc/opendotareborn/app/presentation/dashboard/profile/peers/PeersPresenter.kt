package com.scottandmarc.opendotareborn.app.presentation.dashboard.profile.peers

import android.util.Log
import com.scottandmarc.opendotareborn.app.data.peers.PeerRepository
import com.scottandmarc.opendotareborn.app.data.player.PlayerRepository
import com.scottandmarc.opendotareborn.app.domain.entities.Peer
import com.scottandmarc.opendotareborn.toolbox.helpers.CoroutineScopeProvider
import com.scottandmarc.opendotareborn.toolbox.retrofit.NetworkConnectionChecker
import kotlinx.coroutines.launch
import java.lang.Exception

class PeersPresenter(
    private val accountId: Int,
    private val coroutineScopeProvider: CoroutineScopeProvider,
    private val playerRepository: PlayerRepository,
    private val peerRepository: PeerRepository,
    private val networkConnectionChecker: NetworkConnectionChecker
) : PeersContract.Presenter {

    private var view: PeersContract.View? = null
    private lateinit var peers: List<Peer>
    private lateinit var trimmedPeers: List<Peer>

    private val itemsPerPage = 20
    private var itemsRemaining = 0
    private var lastPage = 0

    override fun onViewReady(view: PeersContract.View) {
        this.view = view
        setup()
    }

    override fun onViewDetach() {
        view = null
    }

    private fun setup() {
        val accountIdFinal = if (accountId == 0) {
            playerRepository.getPlayer().profile.accountId
        } else {
            accountId
        }
        coroutineScopeProvider.provide().launch {
            try {
                view?.showLoadingDialog()

                if (networkConnectionChecker.isNetworkAvailable()) {
                    peers = peerRepository.fetchPeers(accountIdFinal)

                    itemsRemaining = peers.size % itemsPerPage
                    lastPage = peers.size / itemsPerPage

                    trimmedPeers = if (peers.size >= itemsPerPage) {
                        peers.subList(0, itemsPerPage)
                    } else {
                        peers
                    }

                    view?.setPeers(trimmedPeers)
                    view?.updateRv()
                    view?.setTotalPages(lastPage)

                    view?.toggleButtons()
                    view?.setupBtnNext()
                    view?.setupBtnPrev()
                }

                view?.dismissLoadingDialog()
            } catch (e: Exception) {
                Log.d("error", e.localizedMessage?: "")
                throw e
            }
        }
    }

    override fun onPrevBtnClick() {
        val currentPage = view?.getCurrentPage()!!

        generatePeers(currentPage)
    }

    override fun onNextBtnClick() {
        val currentPage = view?.getCurrentPage()!!

        generatePeers(currentPage)
    }

    private fun generatePeers(currentPage: Int) {
        val startItem = (currentPage * itemsPerPage) + 1
        val numOfData = itemsPerPage

        trimmedPeers = if (currentPage == lastPage && itemsRemaining > 0) {
            peers.subList(startItem - 1, (startItem + itemsRemaining) - 1)
        } else {
            peers.subList(startItem - 1, (startItem + numOfData) - 1)
        }
        view?.setPeers(trimmedPeers)
    }
}