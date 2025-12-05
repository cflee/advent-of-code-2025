fun main() {
    fun part1(input: List<String>): Long {
        val freshRanges = mutableListOf<Pair<Long, Long>>()
        var rangeOver = false
        var answer = 0L
        input.forEach { line ->
            if (!rangeOver) {
                if (line == "") {
                    rangeOver = true
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
        for (i in input.indices) {
            if (input[i] == "") break
            val range = input[i].split("-").map { it.toLong() }
            freshRanges.add(range[0] to range[1])
        }
        var end = 0L
        var answer = 0L
        freshRanges.sortWith(compareBy({ it.first }, { it.second }))
        freshRanges.forEach { range ->
            if (end >= range.first) {
                if (end < range.second) {
                    answer += range.second - end
                    end = range.second
                }
            } else {
                end = range.second
                answer += range.second - range.first + 1
            }
        }
        return answer
    }

    val testInput = readInput("Day05_test")
    val input = readInput("Day05")

    part1(testInput).println() // 3
    part1(input).println()

    part2(testInput).println() // 14
    part2(input).println()
}
