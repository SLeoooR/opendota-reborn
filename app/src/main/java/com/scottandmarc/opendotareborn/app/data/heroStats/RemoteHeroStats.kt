package com.scottandmarc.opendotareborn.app.data.heroStats

import com.google.gson.annotations.SerializedName

data class RemoteHeroStats(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("localized_name")
    val localizedName: String,

    @SerializedName("primary_attr")
    val primaryAttr: String,

    @SerializedName("attack_type")
    val attackType: String,

    @SerializedName("roles")
    val roles: List<String>,

    @SerializedName("img")
    val img: String,

    @SerializedName("icon")
    val icon: String,

    @SerializedName("base_health")
    val baseHealth: Int,

    @SerializedName("base_health_regen")
    val baseHealthRegen: Float,

    @SerializedName("base_mana")
    val baseMana: Int,

    @SerializedName("base_mana_regen")
    val baseManaRegen: Float,

    @SerializedName("base_armor")
    val baseArmor: Float,

    @SerializedName("base_mr")
    val baseMagicResist: Int,

    @SerializedName("base_attack_min")
    val baseAttackMin: Int,

    @SerializedName("base_attack_max")
    val baseAttackMax: Int,

    @SerializedName("base_str")
    val baseStr: Int,

    @SerializedName("base_agi")
    val baseAgi: Int,

    @SerializedName("base_int")
    val baseInt: Int,

    @SerializedName("str_gain")
    val strGain: Float,

    @SerializedName("agi_gain")
    val agiGain: Float,

    @SerializedName("int_gain")
    val intGain: Float,

    @SerializedName("attack_range")
    val attackRange: Int,

    @SerializedName("projectile_speed")
    val projectileSpeed: Int,

    @SerializedName("attack_rate")
    val attackRate: Float,

    @SerializedName("move_speed")
    val moveSpeed: Int,

    @SerializedName("turn_rate")
    val turnRate: Float,

    @SerializedName("cm_enabled")
    val cmEnabled: Boolean,

    @SerializedName("legs")
    val legs: Int,

    @SerializedName("hero_id")
    val heroId: Int,

    @SerializedName("turbo_picks")
    val turboPicks: Int,

    @SerializedName("turbo_wins")
    val turboWins: Int,

    @SerializedName("pro_ban")
    val proBan: Int,

    @SerializedName("pro_win")
    val proWin: Int,

    @SerializedName("pro_pick")
    val proPick: Int,

    @SerializedName("1_pick")
    val heraldPick: Int,

    @SerializedName("1_win")
    val heraldWin: Int,

    @SerializedName("2_pick")
    val guardianPick: Int,

    @SerializedName("2_win")
    val guardianWin: Int,

    @SerializedName("3_pick")
    val crusaderPick: Int,

    @SerializedName("3_win")
    val crusaderWin: Int,

    @SerializedName("4_pick")
    val archonPick: Int,

    @SerializedName("4_win")
    val archonWin: Int,

    @SerializedName("5_pick")
    val legendPick: Int,

    @SerializedName("5_win")
    val legendWin: Int,

    @SerializedName("6_pick")
    val ancientPick: Int,

    @SerializedName("6_win")
    val ancientWin: Int,

    @SerializedName("7_pick")
    val divinePick: Int,

    @SerializedName("7_win")
    val divineWin: Int,

    @SerializedName("8_pick")
    val immortalPick: Int,

    @SerializedName("8_win")
    val immortalWin: Int,

    @SerializedName("null_pick")
    val nullPick: Int,

    @SerializedName("null_win")
    val nullWin: Int,
)