package org.example.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class SoloonDto(
    val candidateId: String,
    val row: Int,
    val column: Int,
    val color: String
)
