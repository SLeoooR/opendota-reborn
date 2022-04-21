package com.scottandmarc.opendotareborn.app.data.search

import com.google.gson.annotations.SerializedName

data class RemoteSearch(
    @SerializedName("account_id")
    val accountId: Int,

    @SerializedName("personaname")
    val personaname: String,

    @SerializedName("avatarfull")
    val avatarfull: String,

    @SerializedName("last_match_time")
    val lastMatchTime: String?,

    @SerializedName("similarity")
    val similarity: Float
)