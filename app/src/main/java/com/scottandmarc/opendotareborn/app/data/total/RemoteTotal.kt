package com.scottandmarc.opendotareborn.app.data.total

import com.google.gson.annotations.SerializedName

data class RemoteTotal(
    @SerializedName("field")
    val field: String,

    @SerializedName("n")
    val n: Int,

    @SerializedName("sum")
    val sum: Float
)