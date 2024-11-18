package org.example.mapper

import org.example.model.domain.CelestialBody
import org.example.model.domain.Color
import org.example.model.domain.Direction
import org.example.model.dto.GoalMapDto

fun GoalMapDto.toCelestialBodies(): List<CelestialBody> =
    goal.flatMapIndexed { i, columns ->
        columns.mapIndexed { j, element ->
            when {
                element == "POLYANET" -> CelestialBody.Polyanet(row = i, column = j)
                element.contains("SOLOON") -> CelestialBody.Soloon(
                    row = i,
                    column = j,
                    color = Color.valueOf(element.removeSuffix("_SOLOON"))
                )

                element.contains("COMETH") -> CelestialBody.Cometh(
                    row = i,
                    column = j,
                    direction = Direction.valueOf(element.removeSuffix("_COMETH"))
                )

                else -> null
            }
        }
    }.filterNotNull()
