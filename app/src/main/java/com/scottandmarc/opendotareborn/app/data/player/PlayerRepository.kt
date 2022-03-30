package com.scottandmarc.opendotareborn.app.data.player

import com.scottandmarc.opendotareborn.app.domain.entities.Player
import com.scottandmarc.opendotareborn.app.domain.gateways.PlayerGateway

class PlayerRepository(
    private val playerDao: PlayerDao,
    private val playerService: PlayerEndpoints
) : PlayerGateway {
    override suspend fun fetchPlayer(accountId: Int): Player {
        val playerProfile: RemotePlayer? = playerService.fetchPlayer(accountId).body()
        val playerWinLose: RemoteWinLose? = playerService.fetchWinLose(accountId).body()

        return Player(
            playerProfile?.trackedUntil,
            playerProfile?.soloCompetitiveRank,
            playerProfile?.competitiveRank,
            playerProfile?.rankTier,
            playerProfile?.leaderboardRank,
            Player.MMREstimate(
                playerProfile?.mmrEstimate?.estimate
            ),
            Player.Profile(
                playerProfile?.profile?.accountId ?: 0,
                playerProfile?.profile?.personaName ?: "",
                playerProfile?.profile?.name,
                playerProfile?.profile?.plus ?: false,
                playerProfile?.profile?.cheese ?: 0,
                playerProfile?.profile?.steamId ?: "",
                playerProfile?.profile?.avatar ?: "",
                playerProfile?.profile?.avatarMedium ?: "",
                playerProfile?.profile?.avatarFull ?: "",
                playerProfile?.profile?.profileURL ?: "",
                playerProfile?.profile?.lastLogin,
                playerProfile?.profile?.locCountryCode,
                playerProfile?.profile?.isContributor ?: false,
            ),
            Player.WinLose(
                playerWinLose?.id,
                playerWinLose?.win ?: 0,
                playerWinLose?.lose ?: 0
            )
        )
    }

    // Player DAO
    override fun insert(player: Player) {
        playerDao.insert(LocalPlayer(
            player.profile.accountId,
            player.trackedUntil,
            player.soloCompetitiveRank,
            player.competitiveRank,
            player.rankTier,
            player.leaderboardRank,
            LocalPlayer.LocalMMREstimate(
                player.mmrEstimate.estimate
            ),
            LocalPlayer.LocalProfile(
                player.profile.accountId,
                player.profile.personaName,
                player.profile.name,
                player.profile.plus,
                player.profile.cheese,
                player.profile.steamId,
                player.profile.avatar,
                player.profile.avatarMedium,
                player.profile.avatarFull,
                player.profile.profileURL,
                player.profile.lastLogin,
                player.profile.locCountryCode,
                player.profile.isContributor,
            ),
            LocalPlayer.LocalWinLose(
                player.winLose.win,
                player.winLose.lose
            ),
        ))
    }

    override fun delete() {
        playerDao.delete()
    }

    override fun getPlayer(): Player {
        val localPlayer = playerDao.getPlayer()

        return Player(
            localPlayer.trackedUntil,
            localPlayer.soloCompetitiveRank,
            localPlayer.competitiveRank,
            localPlayer.rankTier,
            localPlayer.leaderboardRank,
            Player.MMREstimate(
                localPlayer.mmrEstimate.estimate
            ),
            Player.Profile(
                localPlayer.profile.accountId,
                localPlayer.profile.personaName,
                localPlayer.profile.name,
                localPlayer.profile.plus,
                localPlayer.profile.cheese,
                localPlayer.profile.steamId,
                localPlayer.profile.avatar,
                localPlayer.profile.avatarMedium,
                localPlayer.profile.avatarFull,
                localPlayer.profile.profileURL,
                localPlayer.profile.lastLogin,
                localPlayer.profile.locCountryCode,
                localPlayer.profile.isContributor,
            ),
            Player.WinLose(
                localPlayer.id,
                localPlayer.winLose.win,
                localPlayer.winLose.lose
            )
        )
    }

    override fun count(): Int {
        return playerDao.count()
    }
}