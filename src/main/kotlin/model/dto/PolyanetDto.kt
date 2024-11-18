package org.example.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class PolyanetDto(
    val candidateId: String,
    val row: Int,
    val column: Int
)