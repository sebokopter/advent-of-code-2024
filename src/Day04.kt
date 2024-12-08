fun main() {
    fun part1(input: List<String>): Int {
        return input.findXmas()
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day04_test.txt` file:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 18)

    // Read the input from the `src/Day04.txt` file.
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}

fun List<String>.rotate(): List<String> {
    val columns = first().length
    val rows = size
    val result = MutableList(columns) { MutableList(rows) { '.' } }
    for (row in 0 until rows) {
        for (column in 0 until columns) {
            result[column][row] = this[row][column]
        }
    }
    return result.map { String(it.toCharArray()) }.toList()
}
fun List<String>.rotate315degree(): List<String> {
    val columns = first().length
    val rows = size
    val result = MutableList(columns) { MutableList(rows) { '.' } }
    for (row in (0 until rows).reversed()) {
        for (column in 0 until columns) {
            result[column][row] = this[row][column]
        }
    }
    return result.map { String(it.toCharArray()) }.toList()
}
fun List<String>.rotate45degree(): List<String> {
    val columns = first().length
    val rows = size
    val result = MutableList(columns) { MutableList(rows) { '.' } }
    for (row in 0 until rows) {
        for (column in (0 until columns).reversed()) {
            result[column][row] = this[row][column]
        }
    }
    return result.map { String(it.toCharArray()) }.toList()
}
fun List<String>.rotate135degree(): List<String> {
    return rotate45degree().rotate()
}
fun List<String>.rotate225degree(): List<String> {
    return rotate315degree().rotate()
}
fun List<String>.findWord(word: String): Int {
    val regex = "$word|${word.reversed()}".toRegex()
    return sumOf {
        regex.findAll(it).count()
    }
}
fun List<String>.findHorizontalXmas(word: String): Int {
    return findWord(word)
}
fun List<String>.findVerticalXmas(word: String): Int {
    return rotate().findWord(word)
}
fun List<String>.findDiagonalXmas(word: String): Int {
    return rotate45degree().findWord(word) +
            rotate315degree().findWord(word) +
            rotate135degree().findWord(word) +
            rotate225degree().findWord(word)
}
fun List<String>.findXmas(): Int {
    return findHorizontalXmas("XMAS") + findVerticalXmas("XMAS") + findDiagonalXmas("XMAS")
}

