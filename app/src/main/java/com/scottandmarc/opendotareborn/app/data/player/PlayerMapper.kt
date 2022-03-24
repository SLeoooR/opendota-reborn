package com.scottandmarc.opendotareborn.app.data.player

import com.scottandmarc.opendotareborn.app.data.player.PlayerMapper.toDomain
import com.scottandmarc.opendotareborn.app.domain.entities.Player

object PlayerMapper {
    fun RemotePlayer.toDomain(): Player = Player(
        this.trackedUntil,
        this.soloCompetitiveRank,
        this.competitiveRank,
        this.rankTier,
        this.leaderboardRank,
        Player.MMREstimate(
            this.mmrEstimate.estimate
        ),
        Player.Profile(
            this.profile.accountId,
            this.profile.personaName,
            this.profile.name,
            this.profile.plus,
            this.profile.cheese,
            this.profile.steamId,
            this.profile.avatar,
            this.profile.avatarMedium,
            this.profile.avatarFull,
            this.profile.profileURL,
            this.profile.lastLogin,
            this.profile.locCountryCode,
            this.profile.isContributor,
        )
    )

    fun Player.toLocal() = LocalPlayer(
        this.profile.accountId,
        this.trackedUntil,
        this.soloCompetitiveRank,
        this.competitiveRank,
        this.rankTier,
        this.leaderboardRank,
        LocalPlayer.LocalMMREstimate(
            this.mmrEstimate.estimate
        ),
        LocalPlayer.LocalProfile(
            this.profile.accountId,
            this.profile.personaName,
            this.profile.name,
            this.profile.plus,
            this.profile.cheese,
            this.profile.steamId,
            this.profile.avatar,
            this.profile.avatarMedium,
            this.profile.avatarFull,
            this.profile.profileURL,
            this.profile.lastLogin,
            this.profile.locCountryCode,
            this.profile.isContributor,
        )
    )

    fun LocalPlayer.toDomain() = Player(
        this.trackedUntil,
        this.soloCompetitiveRank,
        this.competitiveRank,
        this.rankTier,
        this.leaderboardRank,
        Player.MMREstimate(
            this.mmrEstimate.estimate
        ),
        Player.Profile(
            this.profile.accountId,
            this.profile.personaName,
            this.profile.name,
            this.profile.plus,
            this.profile.cheese,
            this.profile.steamId,
            this.profile.avatar,
            this.profile.avatarMedium,
            this.profile.avatarFull,
            this.profile.profileURL,
            this.profile.lastLogin,
            this.profile.locCountryCode,
            this.profile.isContributor,
        )
    )
}