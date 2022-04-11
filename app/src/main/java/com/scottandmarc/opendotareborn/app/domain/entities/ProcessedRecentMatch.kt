package com.scottandmarc.opendotareborn.app.domain.entities

data class ProcessedRecentMatch(
    val stat: String,
    val average: Int,
    val maximum: Int,
    val heroId: Int
)