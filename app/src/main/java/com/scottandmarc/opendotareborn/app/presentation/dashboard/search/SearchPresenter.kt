package com.scottandmarc.opendotareborn.app.presentation.dashboard.search

import android.util.Log
import com.scottandmarc.opendotareborn.app.data.search.SearchRepository
import com.scottandmarc.opendotareborn.app.domain.entities.Search
import com.scottandmarc.opendotareborn.toolbox.helpers.CoroutineScopeProvider
import com.scottandmarc.opendotareborn.toolbox.retrofit.NetworkConnectionChecker
import kotlinx.coroutines.launch

class SearchPresenter(
    private val coroutineScopeProvider: CoroutineScopeProvider,
    private val searchRepository: SearchRepository,
    private val networkConnectionChecker: NetworkConnectionChecker
) : SearchContract.Presenter {

    private var view: SearchContract.View? = null
    private lateinit var searches: List<Search>

    override fun onViewReady(view: SearchContract.View) {
        this.view = view
    }

    override fun onBtnSearchClicked(query: String) {
        coroutineScopeProvider.provide().launch {
            try {
                view?.showLoadingDialog()

                if (networkConnectionChecker.isNetworkAvailable()) {
                    searches = searchRepository.fetchSearches(query)

                    view?.setSearches(searches)
                    view?.updateRv()
                }

                view?.dismissLoadingDialog()
            } catch (e: Exception) {
                Log.d("error", e.localizedMessage?: "")
                throw e
            }
        }
    }

    override fun onViewDetach() {
        view = null
    }
}