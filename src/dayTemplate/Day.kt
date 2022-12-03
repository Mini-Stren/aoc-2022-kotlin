package dayTemplate

import readInputLines

fun main() {

    val day = 0

    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val testInput = readInputLines("/day%02d/input_test".format(day))
    val input = readInputLines("/day%02d/input".format(day))

    check(part1(testInput) == testInput.size)
    println("part1 answer is ${part1(input)}")

    check(part2(testInput) == testInput.size)
    println("part2 answer is ${part2(input)}")
}
