package com.scottandmarc.opendotareborn.app.data.player

import com.google.gson.annotations.SerializedName

data class RemoteWinLose(
    var id: Int,

    @SerializedName("win")
    val win: Int,

    @SerializedName("lose")
    val lose: Int,
)