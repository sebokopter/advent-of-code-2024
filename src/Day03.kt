fun main() {
    fun part1(input: String): Int {
        val regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)".toRegex()
        return regex.findAll(input).sumOf { result ->
            val (a, b) = result.destructured
            a.toInt() * b.toInt()
        }
    }

    fun part2(input: String): Int {
        var enabled = true
        val regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)|don't\\(\\)|do\\(\\)".toRegex()

        return regex.findAll(input).sumOf { result ->
            val operation = result.groupValues[0]
            when {
                operation.startsWith("mul") -> {
                    if (!enabled) return@sumOf 0
                    val (a,b) = result.destructured
                    a.toInt() * b.toInt()
                }

                operation == "don't()" -> {
                    enabled = false
                    0
                }

                operation == "do()" -> {
                    enabled = true
                    0
                }

                else -> {
                    error("unknown operator")
                }
            }
        }
    }

    // Test if implementation meets criteria from the description, like:
    check(part1("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))") == 161)
    check(part2("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))") == 48)

    // Or read a large test input from the `src/Day03_test.txt` file:
//    val testInput = readInput("Day03_test")
//    check(part1(testInput) == 1)

    // Read the input from the `src/Day03.txt` file.
    val input = readInput("Day03").first()
    part1(input).println()
    part2(input).println()
}
