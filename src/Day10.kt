fun main() {
    data class Machine(
        val diagram: String,
        val buttons: List<List<Int>>,
        val joltage: List<Int>
    )

    fun parse(input: List<String>): List<Machine> {
        return input.map { line ->
            val parts = line.split(" ")
            val diagram = parts.first().substring(1, parts.first().length - 1)
            val joltage = parts.last().substring(1, parts.last().length - 1).split(",").map { it.toInt() }
            val buttons = parts.subList(1, parts.size - 1).map { partStr ->
                partStr.substring(1, partStr.length - 1).split(",").map { it.toInt() }
            }
            Machine(
                diagram,
                buttons,
                joltage
            )
        }
    }

    fun toggle(cur: Char) = if (cur == '.') '#' else '.'

    fun part1(input: List<String>): Long {
        val machines = parse(input)
        return machines.sumOf { machine ->
            val memo = mutableMapOf<String, Long>()
            val queue = ArrayDeque<CharArray>()
            val start = CharArray(machine.diagram.length) { '.' }
            memo[start.concatToString()] = 0L
            queue.addLast(start)
            while (queue.isNotEmpty()) {
                val cur = queue.removeFirst()
                val curDist = memo[cur.concatToString()]!!
                machine.buttons.forEach { button ->
                    button.forEach { i ->
                        cur[i] = toggle(cur[i])
                    }
                    val newStr = cur.concatToString()
                    if (!memo.contains(newStr)) {
                        memo[newStr] = curDist + 1
                        queue.addLast(cur.clone())
                    }
                    button.forEach { i ->
                        cur[i] = toggle(cur[i])
                    }
                }
            }
            memo[machine.diagram]!!
        }
    }

    fun part2(input: List<String>): Long {
        return 0L
    }

    val testInput = readInput("Day10_test")
    val input = readInput("Day10")

    part1(testInput).println()
    part1(input).println()

//    part2(testInput).println()
//    part2(input).println()
}
