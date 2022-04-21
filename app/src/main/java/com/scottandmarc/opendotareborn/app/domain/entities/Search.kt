package com.scottandmarc.opendotareborn.app.domain.entities

data class Search(
    val accountId: Int,
    val personaname: String,
    val avatarfull: String,
    val lastMatchTime: String?,
    val similarity: Float
)