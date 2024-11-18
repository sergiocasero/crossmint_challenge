package org.example.mapper

import org.example.model.domain.CelestialBody
import org.example.model.dto.ComethDto
import org.example.model.dto.PolyanetDto
import org.example.model.dto.SoloonDto
import java.util.*

fun CelestialBody.Polyanet.toDto(candidateId: String) =
    PolyanetDto(
        candidateId = candidateId,
        row = row,
        column = column
    )

fun CelestialBody.Soloon.toDto(candidateId: String) =
    SoloonDto(
        candidateId = candidateId,
        row = row,
        column = column,
        color = color.toString().lowercase(Locale.getDefault())
    )

fun CelestialBody.Cometh.toDto(candidateId: String) =
    ComethDto(
        candidateId = candidateId,
        row = row,
        column = column,
        direction = direction.toString().lowercase(Locale.getDefault())
    )