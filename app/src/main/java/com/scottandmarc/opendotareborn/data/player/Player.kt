package com.scottandmarc.opendotareborn.data.player

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "players")
data class Player (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "tracked_until") @SerializedName("tracked_until") val trackedUntil: String,
    @ColumnInfo(name = "solo_competitive_rank") @SerializedName("solo_competitive_rank") val soloCompetitiveRank: Int?,
    @ColumnInfo(name = "competitive_rank") @SerializedName("competitive_rank") val competitiveRank: Int?,
    @ColumnInfo(name = "rank_tier") @SerializedName("rank_tier") val rankTier: Int,
    @ColumnInfo(name = "leaderboard_rank") @SerializedName("leaderboard_rank") val leaderboardRank: Int?,
    @ColumnInfo(name = "mmr_estimate") @SerializedName("mmr_estimate") val mmrEstimate: MMREstimate,
    @ColumnInfo(name = "profile") @SerializedName("profile") val profile: Profile,
) : Parcelable

@Parcelize
data class MMREstimate (
    @ColumnInfo(name = "estimate") @SerializedName("estimate") val estimate: Int,
) : Parcelable

@Parcelize
data class Profile (
    @ColumnInfo(name = "account_id") @SerializedName("account_id") val accountId: Int,
    @ColumnInfo(name = "personaname") @SerializedName("personaname") val personaName: String,
    @ColumnInfo(name = "name") @SerializedName("name") val name: String?,
    @ColumnInfo(name = "plus") @SerializedName("plus") val plus: Boolean,
    @ColumnInfo(name = "cheese") @SerializedName("cheese") val cheese: Int,
    @ColumnInfo(name = "steamid") @SerializedName("steamid") val steamId: String,
    @ColumnInfo(name = "avatar") @SerializedName("avatar") val avatar: String,
    @ColumnInfo(name = "avatarmedium") @SerializedName("avatarmedium") val avatarMedium: String,
    @ColumnInfo(name = "avatarfull") @SerializedName("avatarfull") val avatarFull: String,
    @ColumnInfo(name = "profileurl") @SerializedName("profileurl") val profileURL: String,
    @ColumnInfo(name = "last_login") @SerializedName("last_login") val lastLogin: String,
    @ColumnInfo(name = "loccountrycode") @SerializedName("loccountrycode") val locCountryCode: String,
    @ColumnInfo(name = "is_contributor") @SerializedName("is_contributor") val isContributor: Boolean,
) : Parcelable