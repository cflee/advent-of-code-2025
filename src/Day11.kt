fun main() {
    fun parse(input: List<String>): Map<String, List<String>> {
        val m = mutableMapOf<String, List<String>>()
        input.forEach { line ->
            val parts = line.split(": ")
            val outs = parts[1].split(" ")
            m[parts[0]] = outs
        }
        return m
    }

    fun part1(input: List<String>): Long {
        val m = parse(input)
        val queue = ArrayDeque<String>()
        queue.addLast("you")
        var count = 0L
        while (queue.isNotEmpty()) {
            val cur = queue.removeFirst()
            if (cur == "out") {
                count++
            } else {
                m[cur]?.forEach { dst ->
                    queue.addLast(dst)
                }
            }
        }
        return count
    }

    fun part2(input: List<String>): Long {
        val m = parse(input)
        val memo = mutableMapOf<Triple<String, Boolean, Boolean>, Long>()
        memo[Triple("out", true, true)] = 1L
        fun recurse(cur: String, d: Boolean, f: Boolean): Long {
            val memoKey = Triple(cur, d, f)
            if (memo.containsKey(memoKey)) return memo[memoKey]!!
            var total = 0L
            m[cur]?.forEach { dst ->
                total += recurse(dst, d || dst == "dac", f || dst == "fft")
            }
            memo[memoKey] = total
            return total
        }
        return recurse("svr", false, false)
    }

    val testInput = readInput("Day11_test")
    val input = readInput("Day11")

    part1(testInput).println()
    part1(input).println()

    val testInput2 = readInput("Day11_test2")
    part2(testInput2).println()
    part2(input).println()
}
