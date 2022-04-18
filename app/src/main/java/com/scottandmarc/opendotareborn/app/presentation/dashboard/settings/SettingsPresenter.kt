package com.scottandmarc.opendotareborn.app.presentation.dashboard.settings

import androidx.fragment.app.FragmentManager
import com.scottandmarc.opendotareborn.app.data.player.PlayerRepository
import com.scottandmarc.opendotareborn.toolbox.helpers.CoroutineScopeProvider
import com.scottandmarc.opendotareborn.toolbox.retrofit.NetworkConnectionChecker

class SettingsPresenter(
    private val fragmentManager: FragmentManager,
    coroutineScopeProvider: CoroutineScopeProvider,
    playerRepository: PlayerRepository,
    networkConnectionChecker: NetworkConnectionChecker
) : SettingsContract.Presenter{
    private var view: SettingsContract.View? = null

    private val changeCodeBottomSheet: ChangeCodeBottomSheet = ChangeCodeBottomSheet(
        coroutineScopeProvider,
        playerRepository,
        networkConnectionChecker
    )

    override fun onChangeCodeBtnClick() {
        changeCodeBottomSheet.show(fragmentManager, "Change Code")
    }

    override fun onViewReady(view: SettingsContract.View) {
        this.view = view
    }

    override fun onViewDetach() {
        view = null
    }
}