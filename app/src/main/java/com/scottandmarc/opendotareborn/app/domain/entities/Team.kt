package com.scottandmarc.opendotareborn.app.domain.entities

data class Team(
    val teamId: Int,
    val rating: Float,
    val wins: Int,
    val losses: Int,
    val lastMachTime: Int,
    val name: String,
    val tag: String,
    val logoURL: String?
)
