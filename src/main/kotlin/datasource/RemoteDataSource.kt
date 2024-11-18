package org.example.datasource

import org.example.model.Success
import org.example.model.domain.CelestialBody

interface RemoteDataSource {
    suspend fun createPolaynet(polyanet: CelestialBody.Polyanet): Result<Success>
    suspend fun createSoloon(soloon: CelestialBody.Soloon): Result<Success>
    suspend fun createCometh(cometh: CelestialBody.Cometh): Result<Success>
    suspend fun getGoalMap(): Result<List<CelestialBody>>
}