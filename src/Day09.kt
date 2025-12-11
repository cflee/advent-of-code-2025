import kotlin.math.abs
import kotlin.math.max

fun main() {
    fun part1(input: List<String>): Long {
        val tiles = input.map { it.split(",").map { it.toInt() } }
        var bestArea = 0L
        for (i in 0..<tiles.size) {
            for (j in i + 1..<tiles.size) {
                val area = (abs(tiles[i][0] - tiles[j][0]) + 1).toLong() *
                    (abs(tiles[i][1] - tiles[j][1]) + 1)
                if (area > bestArea) {
                    bestArea = area
                }
            }
        }
        return bestArea
    }

    fun part2(input: List<String>): Long {
        return 0L
    }

    val testInput = readInput("Day09_test")
    val input = readInput("Day09")

    part1(testInput).println() // 50
    part1(input).println()

//    part2(testInput).println()
//    part2(input).println()
}
