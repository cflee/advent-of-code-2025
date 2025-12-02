fun main() {
    fun part1(input: List<String>): Long {
        return input[0].split(",")
            .sumOf { rangeStr ->
                val (start, end) = rangeStr.split("-").map { it.toLong() }
                (start..end).sumOf { num ->
                    val str = num.toString()
                    if (str.length % 2 == 0
                        && str.substring(0, str.length / 2) == str.substring(str.length / 2)
                    ) num else 0L
                }
            }
    }

    fun part2(input: List<String>): Long {
        return input[0].split(",")
            .sumOf { rangeStr ->
                val (start, end) = rangeStr.split("-").map { it.toLong() }
                (start..end).sumOf { num ->
                    val str = num.toString()
                    if ((1..str.length / 2)
                            .filter { str.length % it == 0 }
                            .any { len ->
                                str.substring(0, len).repeat(str.length / len) == str
                            }
                    ) num else 0L
                }
            }
    }

    val testInput = readInput("Day02_test")
    println(part1(testInput)) // 1_227_775_554
    println(part2(testInput)) // 4_174_379_265

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
