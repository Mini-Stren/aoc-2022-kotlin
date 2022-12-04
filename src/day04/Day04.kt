package day04

import readInputLines

fun main() {

    val day = 4

    fun part1(input: List<String>): Int {
        return input.elvesAssignments.count { (firstElfAssignment, secondElfAssignment) ->
            fullyContainsOneAnother(firstElfAssignment, secondElfAssignment)
        }
    }

    fun part2(input: List<String>): Int {
        return input.elvesAssignments.count { (firstElfAssignment, secondElfAssignment) ->
            (firstElfAssignment intersect secondElfAssignment).isNotEmpty()
        }
    }

    val testInput = readInputLines("/day%02d/input_test".format(day))
    val input = readInputLines("/day%02d/input".format(day))

    check(part1(testInput) == 2)
    println("part1 answer is ${part1(input)}")

    check(part2(testInput) == 4)
    println("part2 answer is ${part2(input)}")
}

private val List<String>.elvesAssignments: List<Pair<IntRange, IntRange>>
    get() = map { line ->
        line.split(",")
            .map { it.split("-") }
            .map { IntRange(it[0].toInt(), it[1].toInt()) }
            .let { it[0] to it[1] }
    }

private infix operator fun IntRange.contains(otherRange: IntRange): Boolean {
    val intersection = this intersect otherRange
    return otherRange.toSet() == intersection
}

private fun fullyContainsOneAnother(firstRange: IntRange, secondRange: IntRange): Boolean {
    return firstRange contains secondRange || secondRange contains firstRange
}
