fun main() {
    fun part1(input: List<String>): Long {
        var sR = 0
        var sC = 0
        outer@
        for (r in 0..<input.size) {
            for (c in 0..<input[0].length) {
                if (input[r][c] == 'S') {
                    sR = r
                    sC = c
                    break@outer
                }
            }
        }
        var found = mutableSetOf<Pair<Int, Int>>()
        fun beam(r: Int, c: Int) {
            if (r < 0 || r >= input.size) return
            if (c < 0 || c >= input[0].length) return
            if (input[r][c] == '^') {
                val cur = Pair(r, c)
                if (!found.contains(cur)) {
                    found.add(cur)
                    beam(r + 1, c - 1)
                    beam(r + 1, c + 1)
                }
            } else {
                beam(r + 1, c)
            }
        }
        beam(sR, sC)
        return found.size.toLong()
    }

    fun part2(input: List<String>): Long {
        var sR = 0
        var sC = 0
        outer@
        for (r in 0..<input.size) {
            for (c in 0..<input[0].length) {
                if (input[r][c] == 'S') {
                    sR = r
                    sC = c
                    break@outer
                }
            }
        }
        val memo = mutableMapOf<Pair<Int, Int>, Long>()
        fun beam(r: Int, c: Int): Long {
            return memo.getOrPut(Pair(r, c)) {
                if (r < 0 || r >= input.size) return@getOrPut 0L
                if (c < 0 || c >= input[0].length) return@getOrPut 0L
                if (r == input.size - 1) return@getOrPut 1L
                var result = 0L
                if (input[r][c] == '^') {
                    result += beam(r + 1, c - 1)
                    result += beam(r + 1, c + 1)
                } else {
                    result += beam(r + 1, c)
                }
                result
            }
        }
        return beam(sR, sC)
    }

    val testInput = readInput("Day07_test")
    val input = readInput("Day07")

    part1(testInput).println() // 21
    part1(input).println()

    part2(testInput).println() // 40
    part2(input).println()
}
