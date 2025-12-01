fun main() {
    fun part1(input: List<String>): Int {
        var cur = 50
        var ans = 0
        input.forEach { line ->
            val isLeft = line[0] == 'L'
            val num = line.substring(1).toInt()
            cur = (100 + cur + (if (isLeft) -num else num)) % 100
            if (cur == 0) ans++
        }
        return ans
    }

    fun part2(input: List<String>): Int {
        var cur = 50
        var ans = 0
        input.forEach { line ->
            val isLeft = line[0] == 'L'
            val num = line.substring(1).toInt()
            ans += num / 100
            val after = cur + (if (isLeft) -(num % 100) else (num % 100))
            if ((cur > 0 && after < 0) || after > 100) ans++
            cur = after.mod(100)
            if (cur == 0) ans++
        }
        return ans
    }

    val testInput = readInput("Day01_test")
    println(part1(testInput)) // 3
    println(part2(testInput)) // 6

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
