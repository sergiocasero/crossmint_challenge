import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.example.datasource.KtorRemoteDataSource
import org.example.datasource.RemoteDataSource
import org.example.model.domain.CelestialBody

fun main() {
    val remote: RemoteDataSource = KtorRemoteDataSource(
        endpoint = "https://challenge.crossmint.com/api",
        candidateId = "candidate_id"
    )
    phase1("candidate_id", remote)
}

fun phase1(candidateId: String, remote: RemoteDataSource) {

    val rows = 11
    val columns = 11
    val threshold = 2

    val space = Array(rows) { IntArray(columns) }

    runBlocking {
        val coroutines = space.indices.flatMap { i ->
            space[i].indices.mapNotNull { j ->
                if (shouldCreatePolyanet(i, j, threshold, rows)) {
                    space[i][j] = 1
                    async { remote.createPolaynet(CelestialBody.Polyanet(row = i, column = j)) }
                } else null
            }
        }
        coroutines.awaitAll()
    }

    space.printMatrix()
}

fun shouldCreatePolyanet(i: Int, j: Int, threshold: Int, size: Int): Boolean {
    return (i == j && i in threshold until size - threshold) ||
            (i == size - 1 - j && i in threshold until size - threshold)
}

fun Array<IntArray>.printMatrix() {
    for (i in this.indices) {
        for (j in this[i].indices) {
            print("${this[i][j]} ")
        }
        println()
    }
}