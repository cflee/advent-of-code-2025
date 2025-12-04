fun main() {
    fun part1(input: List<String>): Long {
        val dirs = arrayOf(
            intArrayOf(-1, -1),
            intArrayOf(-1, 0),
            intArrayOf(-1, 1),
            intArrayOf(0, -1),
            intArrayOf(0, +1),
            intArrayOf(1, -1),
            intArrayOf(1, 0),
            intArrayOf(1, 1),
        )
        val cells = input.map { it.toList() }
        var answer = 0L
        for (r in cells.indices) {
            for (c in cells[r].indices) {
                if (cells[r][c] == '.') continue
                val rolls = dirs.count { (dr, dc) ->
                    val nr = r + dr
                    val nc = c + dc
                    nr in 0..<cells.size && nc in 0..<cells[r].size && cells[nr][nc] == '@'
                }
                if (rolls < 4) {
                    answer++
                }
            }
        }
        return answer
    }

    fun part2(input: List<String>): Long {
        val dirs = arrayOf(
            intArrayOf(-1, -1),
            intArrayOf(-1, 0),
            intArrayOf(-1, 1),
            intArrayOf(0, -1),
            intArrayOf(0, +1),
            intArrayOf(1, -1),
            intArrayOf(1, 0),
            intArrayOf(1, 1),
        )
        val cells = input.map { it.toMutableList() }
        var answer = 0L
        fun recurse(r: Int, c: Int) {
            if (cells[r][c] == '.') return
            val rolls = dirs.count { (dr, dc) ->
                val nr = r + dr
                val nc = c + dc
                nr in 0..<cells.size && nc in 0..<cells[r].size && cells[nr][nc] == '@'
            }
            if (rolls < 4) {
                answer++
                cells[r][c] = '.'
                dirs.forEach { (dr, dc) ->
                    val nr = r + dr
                    val nc = c + dc
                    if (nr in 0..<cells.size && nc in 0..<cells[r].size && cells[nr][nc] == '@') {
                        recurse(nr, nc)
                    }
                }
            }
        }
        for (r in cells.indices) {
            for (c in cells[r].indices) {
                recurse(r, c)
            }
        }
        return answer
    }

    val testInput = readInput("Day04_test")
    val input = readInput("Day04")

    part1(testInput).println() // 13
    part1(input).println()

    part2(testInput).println() // 43
    part2(input).println()
}
