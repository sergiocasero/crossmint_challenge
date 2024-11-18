package org.example.datasource

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import org.example.mapper.toCelestialBodies
import org.example.mapper.toDto
import org.example.model.Success
import org.example.model.domain.CelestialBody
import org.example.model.dto.GoalMapDto

class KtorRemoteDataSource(
    private val endpoint: String = "https://challenge.crossmint.com/api",
    private val candidateId: String
) : RemoteDataSource {

    private val client: HttpClient
        get() = HttpClient(CIO) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json()
            }

            defaultRequest {
                contentType(ContentType.Application.Json)
                url {
                    parameters.append("candidateId", candidateId)
                }
            }
        }


    override suspend fun createPolaynet(polyanet: CelestialBody.Polyanet): Result<Success> = execute {
        client.use {
            it.post("$endpoint/polyanets") {
                setBody(polyanet.toDto(candidateId))
            }
        }
        Success
    }

    override suspend fun createSoloon(soloon: CelestialBody.Soloon): Result<Success> = execute {
        client.use {
            it.post("$endpoint/soloons") {
                setBody(soloon.toDto(candidateId))
            }
        }
        Success
    }

    override suspend fun createCometh(cometh: CelestialBody.Cometh): Result<Success> = execute {
        client.use {
            it.post("$endpoint/comeths") {
                setBody(cometh.toDto(candidateId))
            }
        }
        Success
    }

    override suspend fun getGoalMap(): Result<List<CelestialBody>> = execute {
        client.use {
            it.get("$endpoint/map/$candidateId/goal")
        }.body<GoalMapDto>().toCelestialBodies()
    }

    private suspend fun <T> execute(f: suspend () -> T): Result<T> = try {
        Result.success(f())
    } catch (e: Exception) {
        Result.failure(e)
    }
}