import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.example.datasource.KtorRemoteDataSource
import org.example.datasource.RemoteDataSource
import org.example.model.domain.CelestialBody

fun main() = runBlocking {
    val remote: RemoteDataSource = KtorRemoteDataSource(
        endpoint = "https://challenge.crossmint.com/api",
        candidateId = "candidate_id"
    )

    val goalResponse = remote.getGoalMap()
    goalResponse.fold(
        onFailure = { println("Failed to get goal map: ${it.message}") },
        onSuccess = { celestialBodies ->
            celestialBodies.forEach { celestialBody ->
                try {
                    when (celestialBody) {
                        is CelestialBody.Cometh -> remote.createCometh(celestialBody)
                        is CelestialBody.Polyanet -> remote.createPolaynet(celestialBody)
                        is CelestialBody.Soloon -> remote.createSoloon(celestialBody)
                    }.fold(
                        onSuccess = { println("Successfully created ${celestialBody::class.simpleName}") },
                        onFailure = { println("Failed to create ${celestialBody::class.simpleName}") }
                    )
                    delay(1000)
                } catch (e: Exception) {
                    println("Error processing ${celestialBody::class.simpleName}: ${e.message}")
                }
            }
        }
    )
}
