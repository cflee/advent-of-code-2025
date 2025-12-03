import kotlin.math.max
import kotlin.math.pow
import kotlin.time.measureTime

fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            val batts = line.toList().map { it.digitToInt() }
            var best = batts.last()
            var rightBest = batts.last()
            for (i in batts.size - 2 downTo 0) {
                val cur = batts[i] * 10 + rightBest
                best = max(best, cur)
                rightBest = max(rightBest, batts[i])
            }
            best
        }
    }

    fun part2(input: List<String>): Long {
        return input.sumOf { line ->
            val batts = line.toList().map { it.digitToInt() }
            val memo = mutableMapOf<Pair<Int, Int>, Long>()
            fun recurse(startIdx: Int, thisPos: Int): Long {
                return memo.getOrPut(Pair(startIdx, thisPos)) {
                    if (thisPos == 12) {
                        return 0L
                    }
                    (startIdx..(batts.size - (12 - thisPos))).map { idx ->
                        Pair(idx, batts[idx])
                    }.maxOf { (idx, batt) ->
                        (batt * 10.0.pow(11 - thisPos).toLong()) + recurse(idx + 1, thisPos + 1)
                    }
                }
            }
            recurse(0, 0)
        }
    }

    fun part2v2(input: List<String>): Long {
        return input.sumOf { line ->
            val batts = line.toList().map { it.digitToInt() }
            fun recurse(startIdx: Int, thisPos: Int): Long {
                if (thisPos == 12) {
                    return 0L
                }
                (startIdx..(batts.size - (12 - thisPos))).map { idx ->
                    Pair(idx, batts[idx])
                }.sortedWith(compareBy({ -it.second }, { it.first })).forEach { (idx, batt) ->
                    val result = recurse(idx + 1, thisPos + 1)
                    if (result != -1L) {
                        return (batt * 10.0.pow(11 - thisPos).toLong()) + result
                    }
                }
                return -1L
            }
            recurse(0, 0)
        }
    }

    val testInput = readInput("Day03_test")
    val input = readInput("Day03")

    part1(testInput).println() // 357
    part1(input).println()

    part2(testInput).println() // 3121910778619
    part2v2(testInput).println()
    part2(input).println()
    part2v2(input).println()

    val elapsedV1 = measureTime {
        part2(input)
    }
    val elapsedV2 = measureTime {
        part2v2(input)
    }
    println(elapsedV1) // 500ms
    println(elapsedV2) // 6ms
}
