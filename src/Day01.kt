import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val (firstGroup, secondGroup) = getSortedNumberColumns(input)
        return firstGroup.zip(secondGroup) { first, second ->
            calcDistance(first, second)
        }.sum()
    }

    fun part2(input: List<String>): Int {
        val (firstGroup, secondGroup) = splitByColumn(input)
        return firstGroup.sumOf { element ->
            val similarityScore = secondGroup.count { it == element }
            element * similarityScore
        }
    }


    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

fun calcDistance(a: Int, b: Int): Int = abs(a - b)

fun splitByColumn(input: List<String>): Pair<List<Int>, List<Int>> = input.map { line ->
    val (first, second) = line.split("   ")
    first.toInt() to second.toInt()
}.unzip()

fun getSortedNumberColumns(input: List<String>): Pair<List<Int>, List<Int>> {
    val (firstGroup, secondGroup) = splitByColumn(input)
    return firstGroup.sorted() to secondGroup.sorted()
}