package day03

import readInputLines

fun main() {

    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            line.chunked(line.length / 2, CharSequence::toSet)
                .let { it[0] intersect it[1] }
                .first()
                .let(Char::priority)
        }
    }

    fun part2(input: List<String>): Int {
        return input.chunked(3) { elvesGroup ->
            elvesGroup.map(String::toSet)
                .let { it[0] intersect it[1] intersect it[2] }
                .first()
        }.sumOf(Char::priority)
    }

    val testInput = readInputLines("/day03/Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInputLines("/day03/Day03")
    println(part1(input))
    println(part2(input))
}

private val Char.priority: Int
    get() = 1 + when {
        isUpperCase() -> this - 'A' + 26
        else -> this - 'a'
    }
