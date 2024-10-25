package com.scottandmarc.opendotareborn.app.data.matchDetails
import com.google.gson.annotations.SerializedName


data class RemoteMatchDetails(
    @SerializedName("match_id")
    val matchId: Long,
    @SerializedName("barracks_status_dire")
    val barracksStatusDire: Int?,
    @SerializedName("barracks_status_radiant")
    val barracksStatusRadiant: Int?,
    @SerializedName("chat")
    val chat: Any?,
    @SerializedName("cluster")
    val cluster: Int?,
    @SerializedName("cosmetics")
    val cosmetics: Any?,
    @SerializedName("dire_score")
    val direScore: Int?,
    @SerializedName("dire_team_id")
    val direTeamId: Any?,
    @SerializedName("draft_timings")
    val draftTimings: Any?,
    @SerializedName("duration")
    val duration: Int?,
    @SerializedName("engine")
    val engine: Int?,
    @SerializedName("first_blood_time")
    val firstBloodTime: Int?,
    @SerializedName("game_mode")
    val gameMode: Int?,
    @SerializedName("human_players")
    val humanPlayers: Int?,
    @SerializedName("leagueid")
    val leagueid: Int?,
    @SerializedName("lobby_type")
    val lobbyType: Int?,
    @SerializedName("match_seq_num")
    val matchSeqNum: Long?,
    @SerializedName("negative_votes")
    val negativeVotes: Int?,
    @SerializedName("objectives")
    val objectives: Any?,
    @SerializedName("picks_bans")
    val picksBans: List<RemotePicksBan>?,
    @SerializedName("positive_votes")
    val positiveVotes: Int?,
    @SerializedName("radiant_gold_adv")
    val radiantGoldAdv: Any?,
    @SerializedName("radiant_score")
    val radiantScore: Int?,
    @SerializedName("radiant_team_id")
    val radiantTeamId: Any?,
    @SerializedName("radiant_win")
    val radiantWin: Boolean?,
    @SerializedName("radiant_xp_adv")
    val radiantXpAdv: Any?,
    @SerializedName("skill")
    val skill: Any?,
    @SerializedName("start_time")
    val startTime: Int?,
    @SerializedName("teamfights")
    val teamfights: Any?,
    @SerializedName("tower_status_dire")
    val towerStatusDire: Int?,
    @SerializedName("tower_status_radiant")
    val towerStatusRadiant: Int?,
    @SerializedName("version")
    val version: Any?,
    @SerializedName("replay_salt")
    val replaySalt: Int?,
    @SerializedName("series_id")
    val seriesId: Int?,
    @SerializedName("series_type")
    val seriesType: Int?,
    @SerializedName("players")
    val players: List<RemoteMatchPlayer>?,
    @SerializedName("patch")
    val patch: Int?,
    @SerializedName("region")
    val region: Int?,
    @SerializedName("replay_url")
    val replayUrl: String?
)

data class RemotePicksBan(
    @SerializedName("is_pick")
    val isPick: Boolean?,
    @SerializedName("hero_id")
    val heroId: Int?,
    @SerializedName("team")
    val team: Int?,
    @SerializedName("order")
    val order: Int?
)

data class RemoteMatchPlayer(
    @SerializedName("match_id")
    val matchId: Long?,
    @SerializedName("player_slot")
    val playerSlot: Int?,
    @SerializedName("ability_targets")
    val abilityTargets: Any?,
    @SerializedName("ability_upgrades_arr")
    val abilityUpgradesArr: List<Int>?,
    @SerializedName("ability_uses")
    val abilityUses: Any?,
    @SerializedName("account_id")
    val accountId: Int?,
    @SerializedName("actions")
    val actions: Any?,
    @SerializedName("additional_units")
    val additionalUnits: List<RemoteAdditionalUnit>?,
    @SerializedName("assists")
    val assists: Int?,
    @SerializedName("backpack_0")
    val backpack0: Int?,
    @SerializedName("backpack_1")
    val backpack1: Int?,
    @SerializedName("backpack_2")
    val backpack2: Int?,
    @SerializedName("backpack_3")
    val backpack3: Any?,
    @SerializedName("buyback_log")
    val buybackLog: Any?,
    @SerializedName("camps_stacked")
    val campsStacked: Any?,
    @SerializedName("connection_log")
    val connectionLog: Any?,
    @SerializedName("creeps_stacked")
    val creepsStacked: Any?,
    @SerializedName("damage")
    val damage: Any?,
    @SerializedName("damage_inflictor")
    val damageInflictor: Any?,
    @SerializedName("damage_inflictor_received")
    val damageInflictorReceived: Any?,
    @SerializedName("damage_taken")
    val damageTaken: Any?,
    @SerializedName("damage_targets")
    val damageTargets: Any?,
    @SerializedName("deaths")
    val deaths: Int?,
    @SerializedName("denies")
    val denies: Int?,
    @SerializedName("dn_t")
    val dnT: Any?,
    @SerializedName("firstblood_claimed")
    val firstbloodClaimed: Any?,
    @SerializedName("gold")
    val gold: Int?,
    @SerializedName("gold_per_min")
    val goldPerMin: Int?,
    @SerializedName("gold_reasons")
    val goldReasons: Any?,
    @SerializedName("gold_spent")
    val goldSpent: Int?,
    @SerializedName("gold_t")
    val goldT: Any?,
    @SerializedName("hero_damage")
    val heroDamage: Int?,
    @SerializedName("hero_healing")
    val heroHealing: Int?,
    @SerializedName("hero_hits")
    val heroHits: Any?,
    @SerializedName("hero_id")
    val heroId: Int?,
    @SerializedName("item_0")
    val item0: Int?,
    @SerializedName("item_1")
    val item1: Int?,
    @SerializedName("item_2")
    val item2: Int?,
    @SerializedName("item_3")
    val item3: Int?,
    @SerializedName("item_4")
    val item4: Int?,
    @SerializedName("item_5")
    val item5: Int?,
    @SerializedName("item_neutral")
    val itemNeutral: Int?,
    @SerializedName("item_uses")
    val itemUses: Any?,
    @SerializedName("kill_streaks")
    val killStreaks: Any?,
    @SerializedName("killed")
    val killed: Any?,
    @SerializedName("killed_by")
    val killedBy: Any?,
    @SerializedName("kills")
    val kills: Int?,
    @SerializedName("kills_log")
    val killsLog: Any?,
    @SerializedName("lane_pos")
    val lanePos: Any?,
    @SerializedName("last_hits")
    val lastHits: Int?,
    @SerializedName("leaver_status")
    val leaverStatus: Int?,
    @SerializedName("level")
    val level: Int?,
    @SerializedName("lh_t")
    val lhT: Any?,
    @SerializedName("life_state")
    val lifeState: Any?,
    @SerializedName("max_hero_hit")
    val maxHeroHit: Any?,
    @SerializedName("multi_kills")
    val multiKills: Any?,
    @SerializedName("net_worth")
    val netWorth: Int?,
    @SerializedName("obs")
    val obs: Any?,
    @SerializedName("obs_left_log")
    val obsLeftLog: Any?,
    @SerializedName("obs_log")
    val obsLog: Any?,
    @SerializedName("obs_placed")
    val obsPlaced: Any?,
    @SerializedName("party_id")
    val partyId: Int?,
    @SerializedName("party_size")
    val partySize: Int?,
    @SerializedName("performance_others")
    val performanceOthers: Any?,
    @SerializedName("permanent_buffs")
    val permanentBuffs: List<RemotePermanentBuff>?,
    @SerializedName("pings")
    val pings: Any?,
    @SerializedName("pred_vict")
    val predVict: Any?,
    @SerializedName("purchase")
    val purchase: Any?,
    @SerializedName("purchase_log")
    val purchaseLog: Any?,
    @SerializedName("randomed")
    val randomed: Any?,
    @SerializedName("repicked")
    val repicked: Any?,
    @SerializedName("roshans_killed")
    val roshansKilled: Any?,
    @SerializedName("rune_pickups")
    val runePickups: Any?,
    @SerializedName("runes")
    val runes: Any?,
    @SerializedName("runes_log")
    val runesLog: Any?,
    @SerializedName("sen")
    val sen: Any?,
    @SerializedName("sen_left_log")
    val senLeftLog: Any?,
    @SerializedName("sen_log")
    val senLog: Any?,
    @SerializedName("sen_placed")
    val senPlaced: Any?,
    @SerializedName("stuns")
    val stuns: Any?,
    @SerializedName("teamfight_participation")
    val teamfightParticipation: Any?,
    @SerializedName("times")
    val times: Any?,
    @SerializedName("tower_damage")
    val towerDamage: Int?,
    @SerializedName("towers_killed")
    val towersKilled: Any?,
    @SerializedName("xp_per_min")
    val xpPerMin: Int?,
    @SerializedName("xp_reasons")
    val xpReasons: Any?,
    @SerializedName("xp_t")
    val xpT: Any?,
    @SerializedName("personaname")
    val personaname: String?,
    @SerializedName("name")
    val name: Any?,
    @SerializedName("last_login")
    val lastLogin: String?,
    @SerializedName("radiant_win")
    val radiantWin: Boolean?,
    @SerializedName("start_time")
    val startTime: Int?,
    @SerializedName("duration")
    val duration: Int?,
    @SerializedName("cluster")
    val cluster: Int?,
    @SerializedName("lobby_type")
    val lobbyType: Int?,
    @SerializedName("game_mode")
    val gameMode: Int?,
    @SerializedName("is_contributor")
    val isContributor: Boolean?,
    @SerializedName("patch")
    val patch: Int?,
    @SerializedName("region")
    val region: Int?,
    @SerializedName("isRadiant")
    val isRadiant: Boolean?,
    @SerializedName("win")
    val win: Int?,
    @SerializedName("lose")
    val lose: Int?,
    @SerializedName("total_gold")
    val totalGold: Int?,
    @SerializedName("total_xp")
    val totalXp: Int?,
    @SerializedName("kills_per_min")
    val killsPerMin: Double?,
    @SerializedName("kda")
    val kda: Int?,
    @SerializedName("abandons")
    val abandons: Int?,
    @SerializedName("rank_tier")
    val rankTier: Int?,
    @SerializedName("is_subscriber")
    val isSubscriber: Boolean?,
    @SerializedName("cosmetics")
    val cosmetics: List<Any>?,
    @SerializedName("benchmarks")
    val benchmarks: RemoteBenchmarks?
)

data class RemoteAdditionalUnit(
    @SerializedName("unitname")
    val unitname: String?,
    @SerializedName("item_0")
    val item0: Int?,
    @SerializedName("item_1")
    val item1: Int?,
    @SerializedName("item_2")
    val item2: Int?,
    @SerializedName("item_3")
    val item3: Int?,
    @SerializedName("item_4")
    val item4: Int?,
    @SerializedName("item_5")
    val item5: Int?,
    @SerializedName("backpack_0")
    val backpack0: Int?,
    @SerializedName("backpack_1")
    val backpack1: Int?,
    @SerializedName("backpack_2")
    val backpack2: Int?,
    @SerializedName("item_neutral")
    val itemNeutral: Int?
)

data class RemotePermanentBuff(
    @SerializedName("permanent_buff")
    val permanentBuff: Int?,
    @SerializedName("stack_count")
    val stackCount: Int?,
    @SerializedName("grant_time")
    val grantTime: Int?
)

data class RemoteBenchmarks(
    @SerializedName("gold_per_min")
    val goldPerMin: RemoteRawPct?,
    @SerializedName("xp_per_min")
    val xpPerMin: RemoteRawPct?,
    @SerializedName("kills_per_min")
    val killsPerMin: RemoteRawPct?,
    @SerializedName("last_hits_per_min")
    val lastHitsPerMin: RemoteRawPct?,
    @SerializedName("hero_damage_per_min")
    val heroDamagePerMin: RemoteRawPct?,
    @SerializedName("hero_healing_per_min")
    val heroHealingPerMin: RemoteRawPct?,
    @SerializedName("tower_damage")
    val towerDamage: RemoteRawPct?,
    @SerializedName("stuns_per_min")
    val stunsPerMin: RemoteRawPct?,
    @SerializedName("lhten")
    val lhten: RemoteRawPct?
)

data class RemoteRawPct(
    @SerializedName("raw")
    val raw: Int?,
    @SerializedName("pct")
    val pct: Double?
)
