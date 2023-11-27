package com.example.stockapp.models

data class CompetitionState(
    val score: Int = 0,
    val ranking: List<UserRank> = emptyList(),
)

data class UserRank(
    val username: String,
    val points: Int
)