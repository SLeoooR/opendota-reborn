package com.scottandmarc.opendotareborn.app.domain.entities

data class HeroInfo(
    val id: Int,
    val name: String,
    val localizedName: String,
    val primaryAttr: String,
    val attackType: String,
    val roles: List<String>,
)