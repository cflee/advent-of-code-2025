import kotlin.math.max

fun main() {
    fun part1(input: List<String>): Long {
        val freshRanges = mutableListOf<Pair<Long, Long>>()
        var rangeOver = false
        var answer = 0L
        input.forEach { line ->
            if (!rangeOver) {
                if (line == "") {
                    rangeOver = true
                    freshRanges.sortWith(compareBy({ it.first }, { it.second }))
                    return@forEach
                }
                val range = line.split("-").map { it.toLong() }
                freshRanges.add(range[0] to range[1])
            } else {
                val query = line.toLong()
                if (freshRanges.any { range -> query in range.first..range.second }) {
                    answer++
                }
            }
        }
        return answer
    }

    fun part2(input: List<String>): Long {
        val freshRanges = mutableListOf<Pair<Long, Long>>()
        var rangeOver = false
        input.forEach { line ->
            if (!rangeOver) {
                if (line == "") {
                    rangeOver = true
                    return@forEach
                }
                val range = line.split("-").map { it.toLong() }
                freshRanges.add(range[0] to range[1])
            }
        }
        freshRanges.sortWith(compareBy({ it.first }, { it.second }))
        val mergedRanges = mutableListOf<Pair<Long, Long>>()
        freshRanges.forEach { range ->
            if (mergedRanges.isNotEmpty() && mergedRanges.last().second >= range.first) {
                val newRange = mergedRanges.last().first to max(mergedRanges.last().second, range.second)
                mergedRanges.removeLast()
                mergedRanges.add(newRange)
            } else {
                mergedRanges.add(range)
            }
        }
        return mergedRanges.sumOf { it.second - it.first + 1 }
    }

    val testInput = readInput("Day05_test")
    val input = readInput("Day05")

    part1(testInput).println() // 3
    part1(input).println()

    part2(testInput).println() // 14
    part2(input).println()
}
