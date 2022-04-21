package com.scottandmarc.opendotareborn.app.data.heroStats

import android.util.Log
import com.scottandmarc.opendotareborn.app.domain.entities.HeroStats
import com.scottandmarc.opendotareborn.app.domain.gateways.HeroStatsGateway
import java.lang.Exception

class HeroStatsRepository(
    private val heroStatsEndpoints: HeroStatsEndpoints
) : HeroStatsGateway {
    override suspend fun fetchHeroesStats(): List<HeroStats> {
        return try {
            val fetchedHeroStats = heroStatsEndpoints.fetchHeroesStats().body()!!

            fun RemoteHeroStats.toDomain(): HeroStats {
                return HeroStats(
                    this.id,
                    this.name,
                    this.localizedName,
                    this.primaryAttr,
                    this.attackType,
                    this.roles,
                    this.img,
                    this.icon,
                    this.baseHealth,
                    this.baseHealthRegen,
                    this.baseMana,
                    this.baseManaRegen,
                    this.baseArmor,
                    this.baseMagicResist,
                    this.baseAttackMin,
                    this.baseAttackMax,
                    this.baseStr,
                    this.baseAgi,
                    this.baseInt,
                    this.strGain,
                    this.agiGain,
                    this.intGain,
                    this.attackRange,
                    this.projectileSpeed,
                    this.attackRate,
                    this.moveSpeed,
                    this.turnRate,
                    this.cmEnabled,
                    this.legs,
                    this.heroId,
                    this.turboPicks,
                    this.turboWins,
                    this.proBan,
                    this.proWin,
                    this.proPick,
                    this.heraldPick,
                    this.heraldWin,
                    this.guardianPick,
                    this.guardianWin,
                    this.crusaderPick,
                    this.crusaderWin,
                    this.archonPick,
                    this.archonWin,
                    this.legendPick,
                    this.legendWin,
                    this.ancientPick,
                    this.ancientWin,
                    this.divinePick,
                    this.divineWin,
                    this.immortalPick,
                    this.immortalWin,
                    this.nullPick,
                    this.nullWin,
                    if (this.proWin != 0) ((this.proWin / this.proPick) * 100).toFloat() else this.proWin.toFloat()
                )
            }

            fetchedHeroStats.map {
                it.toDomain()
            }
        } catch (e: Exception) {
            Log.d("error", e.localizedMessage?: "")
            throw e
        }
    }
}