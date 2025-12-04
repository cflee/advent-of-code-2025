#!/bin/sh

# Check if day number is provided
if [ -z "$1" ]; then
    echo "Usage: $0 <day_number>"
    echo "Example: $0 05"
    exit 1
fi

DAY="$1"

cat > "src/Day${DAY}.kt" <<EOF
fun main() {
    fun part1(input: List<String>): Long {
        return 0L
    }

    fun part2(input: List<String>): Long {
        return 0L
    }

    val testInput = readInput("Day${DAY}_test")
    val input = readInput("Day${DAY}")

    part1(testInput).println()
    part1(input).println()

    part2(testInput).println()
    part2(input).println()
}
EOF

# Create blank test and input files
touch "src/Day${DAY}_test.txt"
touch "src/Day${DAY}.txt"

echo "Created files for Day ${DAY}:"
echo "  src/Day${DAY}.kt"
echo "  src/Day${DAY}_test.txt"
echo "  src/Day${DAY}.txt"
