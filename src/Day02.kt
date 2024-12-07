import kotlin.math.abs

fun main() {

    fun findUnsafeLevel(levels: List<Int>): Int {
        var wasIncreasing: Boolean? = null
        for (index in 0 until levels.lastIndex) {
            val currentLevel = levels[index]
            val nextLevel = levels[index + 1]
            val isIncreasing = nextLevel > currentLevel
            if (wasIncreasing == null) {
                wasIncreasing = isIncreasing
            }
            if (abs(nextLevel - currentLevel) !in 1..3) {
                return index + 1
            }
            if (wasIncreasing != isIncreasing) {
                return index + 1
            }
        }
        return -1
    }

    fun isSafe(levels: List<Int>, tolerance: Int = 0): Boolean {
        val unsafeLevel = findUnsafeLevel(levels)
        if (unsafeLevel == -1) {
            // everything's safe
            return true
        }
        if (tolerance <= 0) {
            // No more tolerance left to fix the sequence
            return false
        }
        val newLevels = levels.filterIndexed { index, _ -> index != unsafeLevel }
        return isSafe(newLevels, tolerance - 1)
    }

    fun part1(reports: List<String>): Int {
        return reports.count { report ->
            val levels = report.split(" ").map(String::toInt)
            isSafe(levels)
        }
    }

    fun part2(reports: List<String>): Int {
        return reports.count { report ->
            val levels = report.split(" ").map(String::toInt)
            isSafe(levels, 1)
        }
    }

    // Or read a large test input from the `src/Day02_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    // Read the input from the `src/Day02.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
