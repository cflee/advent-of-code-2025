import java.util.PriorityQueue
import kotlin.math.pow
import kotlin.math.sqrt

fun Int.square(): Double = this.toDouble().pow(2)

fun main() {
    fun part1(input: List<String>, iterations: Int): Int {
        val n = input.size
        val uf = UnionFind(n)
        val boxes = input.map { it.split(",").map { it.toInt() }}
        val minHeap = PriorityQueue<Triple<Double, Int, Int>>(compareBy { it.first })
        for (i in 0..<n) {
            for (j in i + 1..<n) {
                val dist = sqrt((0..2).sumOf { (boxes[i][it] - boxes[j][it]).square() })
                minHeap.add(Triple(dist, i, j))
            }
        }
        for (iter in 0..<iterations) {
            val (_, x, y) = minHeap.poll()
            uf.union(x, y)
        }
        val components = mutableMapOf<Int, Int>()
        for (i in 0..<n) {
            val p = uf.find(i)
            components[p] = uf.size(i)
        }
        return components.values.sortedByDescending { it }.subList(0, 3).reduce { acc, i -> acc * i }
    }

    fun part2(input: List<String>): Long {
        val n = input.size
        val uf = UnionFind(n)
        val boxes = input.map { it.split(",").map { it.toInt() }}
        val minHeap = PriorityQueue<Triple<Double, Int, Int>>(compareBy { it.first })
        for (i in 0..<n) {
            for (j in i + 1..<n) {
                val dist = sqrt((0..2).sumOf { (boxes[i][it] - boxes[j][it]).square() })
                minHeap.add(Triple(dist, i, j))
            }
        }
        var count = n
        while (count > 1) {
            val (_, x, y) = minHeap.poll()
            if (uf.union(x, y)) {
                count--
                if (count == 1) {
                    return boxes[x][0].toLong() * boxes[y][0]
                }
            }
        }
        return -1
    }

    val testInput = readInput("Day08_test")
    val input = readInput("Day08")

    part1(testInput, 10).println() // 40
    part1(input, 1000).println()

    part2(testInput).println() // 25272
    part2(input).println()
}
