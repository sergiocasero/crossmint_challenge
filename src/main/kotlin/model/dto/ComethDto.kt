package org.example.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class ComethDto(
    val candidateId: String,
    val row: Int,
    val column: Int,
    val direction: String
)
