fun main() {
    fun parse1(input: List<String>): List<List<String>> {
        val breaks = mutableListOf<Int>()
        breaks.add(-1)
        outer@
        for (c in 0..<input[0].length) {
            for (r in input.indices) {
                if (input[r][c] != ' ') {
                    continue@outer
                }
            }
            breaks.add(c)
        }
        breaks.add(input[0].length)
        val result = mutableListOf<List<String>>()
        for (i in 1..<breaks.size) {
            result.add(input.map { line ->
                line.substring(breaks[i - 1] + 1, breaks[i])
            })
        }
        return result
    }

    fun part1(input: List<String>): Long {
        return parse1(input).sumOf { problem ->
            problem.subList(0, problem.size - 1).map { it.trim().toLong() }.reduce { u, v ->
                if (problem[problem.size - 1].trim()[0] == '+') {
                    u + v
                } else {
                    u * v
                }
            }
        }
    }

    fun part2(input: List<String>): Long {
        return parse1(input).sumOf { problem ->
            val operands = mutableListOf<Long>()
            for (c in 0..<problem[0].length) {
                val sb = StringBuilder()
                // skip the last row
                for (r in 0..problem.size - 2) {
                    sb.append(problem[r][c])
                }
                operands.add(sb.toString().trim().toLong())
            }
            operands.reduce { u, v ->
                if (problem[problem.size - 1].trim()[0] == '+') {
                    u + v
                } else {
                    u * v
                }
            }
        }
    }

    val testInput = readInputNoTrim("Day06_test")
    val input = readInputNoTrim("Day06")

    part1(testInput).println() // 4277556
    part1(input).println()

    part2(testInput).println() // 3263827
    part2(input).println()
}
