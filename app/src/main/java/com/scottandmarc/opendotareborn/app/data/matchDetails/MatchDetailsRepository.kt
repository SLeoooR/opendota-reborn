package com.scottandmarc.opendotareborn.app.data.matchDetails

import android.util.Log
import com.scottandmarc.opendotareborn.app.domain.entities.*
import com.scottandmarc.opendotareborn.app.domain.gateways.MatchDetailsGateway
import java.lang.Exception

class MatchDetailsRepository(
    private val matchService: MatchDetailsEndpoints
) : MatchDetailsGateway {
    override suspend fun fetchMatchDetails(matchId: Long): MatchDetails {
        return try {
            val fetchedMatchDetails = matchService.fetchMatchDetails(matchId).body()!!

            fun RemotePicksBan.toDomain(): PicksBan {
                return PicksBan(
                    this.isPick,
                    this.heroId,
                    this.team,
                    this.order
                )
            }
            
            fun RemoteAdditionalUnit.toDomain(): AdditionalUnit {
                return AdditionalUnit(
                    this.unitname,
                    this.item0,
                    this.item1,
                    this.item2,
                    this.item3,
                    this.item4,
                    this.item5,
                    this.backpack0,
                    this.backpack1,
                    this.backpack2,
                    this.itemNeutral,
                )
            }

            fun RemotePermanentBuff.toDomain(): PermanentBuff {
                return PermanentBuff(
                    this.permanentBuff,
                    this.stackCount,
                    this.grantTime,
                )
            }
            
            fun RemoteBenchmarks.toDomain(): Benchmarks {
                return Benchmarks(
                    RawPct(this.goldPerMin?.raw, this.goldPerMin?.pct),
                    RawPct(this.xpPerMin?.raw, this.xpPerMin?.pct),
                    RawPct(this.killsPerMin?.raw, this.killsPerMin?.pct),
                    RawPct(this.lastHitsPerMin?.raw, this.lastHitsPerMin?.pct),
                    RawPct(this.heroDamagePerMin?.raw, this.heroDamagePerMin?.pct),
                    RawPct(this.heroHealingPerMin?.raw, this.heroHealingPerMin?.pct),
                    RawPct(this.towerDamage?.raw, this.towerDamage?.pct),
                    RawPct(this.stunsPerMin?.raw, this.stunsPerMin?.pct),
                    RawPct(this.lhten?.raw, this.lhten?.pct)
                )
            }

            fun RemoteMatchPlayer.toDomain(): MatchPlayer {
                return MatchPlayer(
                    this.matchId,
                    this.playerSlot,
                    this.abilityTargets,
                    this.abilityUpgradesArr,
                    this.abilityUses,
                    this.accountId,
                    this.actions,
                    this.additionalUnits?.map {
                        it.toDomain()
                    },
                    this.assists,
                    this.backpack0,
                    this.backpack1,
                    this.backpack2,
                    this.backpack3,
                    this.buybackLog,
                    this.campsStacked,
                    this.connectionLog,
                    this.creepsStacked,
                    this.damage,
                    this.damageInflictor,
                    this.damageInflictorReceived,
                    this.damageTaken,
                    this.damageTargets,
                    this.deaths,
                    this.denies,
                    this.dnT,
                    this.firstbloodClaimed,
                    this.gold,
                    this.goldPerMin,
                    this.goldReasons,
                    this.goldSpent,
                    this.goldT,
                    this.heroDamage,
                    this.heroHealing,
                    this.heroHits,
                    this.heroId,
                    this.item0,
                    this.item1,
                    this.item2,
                    this.item3,
                    this.item4,
                    this.item5,
                    this.itemNeutral,
                    this.itemUses,
                    this.killStreaks,
                    this.killed,
                    this.killedBy,
                    this.kills,
                    this.killsLog,
                    this.lanePos,
                    this.lastHits,
                    this.leaverStatus,
                    this.level,
                    this.lhT,
                    this.lifeState,
                    this.maxHeroHit,
                    this.multiKills,
                    this.netWorth,
                    this.obs,
                    this.obsLeftLog,
                    this.obsLog,
                    this.obsPlaced,
                    this.partyId,
                    this.partySize,
                    this.performanceOthers,
                    this.permanentBuffs?.map {
                        it.toDomain()
                    },
                    this.pings,
                    this.predVict,
                    this.purchase,
                    this.purchaseLog,
                    this.randomed,
                    this.repicked,
                    this.roshansKilled,
                    this.runePickups,
                    this.runes,
                    this.runesLog,
                    this.sen,
                    this.senLeftLog,
                    this.senLog,
                    this.senPlaced,
                    this.stuns,
                    this.teamfightParticipation,
                    this.times,
                    this.towerDamage,
                    this.towersKilled,
                    this.xpPerMin,
                    this.xpReasons,
                    this.xpT,
                    this.personaname,
                    this.name,
                    this.lastLogin,
                    this.radiantWin,
                    this.startTime,
                    this.duration,
                    this.cluster,
                    this.lobbyType,
                    this.gameMode,
                    this.isContributor,
                    this.patch,
                    this.region,
                    this.isRadiant,
                    this.win,
                    this.lose,
                    this.totalGold,
                    this.totalXp,
                    this.killsPerMin,
                    this.kda,
                    this.abandons,
                    this.rankTier,
                    this.isSubscriber,
                    this.cosmetics,
                    this.benchmarks?.toDomain(),
                )
            }
            
            fun RemoteMatchDetails.toDomain(): MatchDetails {
                return MatchDetails(
                    this.matchId,
                    this.barracksStatusDire,
                    this.barracksStatusRadiant,
                    this.chat,
                    this.cluster,
                    this.cosmetics,
                    this.direScore,
                    this.direTeamId,
                    this.draftTimings,
                    this.duration,
                    this.engine,
                    this.firstBloodTime,
                    this.gameMode,
                    this.humanPlayers,
                    this.leagueid,
                    this.lobbyType,
                    this.matchSeqNum,
                    this.negativeVotes,
                    this.objectives,
                    this.picksBans?.map {
                        it.toDomain()
                    },
                    this.positiveVotes,
                    this.radiantGoldAdv,
                    this.radiantScore,
                    this.radiantTeamId,
                    this.radiantWin,
                    this.radiantXpAdv,
                    this.skill,
                    this.startTime,
                    this.teamfights,
                    this.towerStatusDire,
                    this.towerStatusRadiant,
                    this.version,
                    this.replaySalt,
                    this.seriesId,
                    this.seriesType,
                    this.players?.map {
                        it.toDomain()
                    },
                    this.patch,
                    this.region,
                    this.replayUrl,
                )
            }

            fetchedMatchDetails.toDomain()
        } catch (e: Exception) {
            Log.d("error", e.localizedMessage?: "")
            throw e
        }
    }
}