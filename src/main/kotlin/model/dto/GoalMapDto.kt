package org.example.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class GoalMapDto(
    val goal: List<List<String>>
)