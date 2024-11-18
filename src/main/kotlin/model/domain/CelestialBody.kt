package org.example.model.domain

sealed class CelestialBody {
    data class Polyanet(val row: Int, val column: Int) : CelestialBody()
    data class Soloon(val row: Int, val column: Int, val color: Color) : CelestialBody()
    data class Cometh(val row: Int, val column: Int, val direction: Direction) : CelestialBody()
}

enum class Color {
    BLUE, RED, PURPLE, WHITE
}

enum class Direction {
    UP, DOWN, RIGHT, LEFT
}