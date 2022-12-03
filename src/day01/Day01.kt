package day01

import readInputText

fun main() {

    val day = 1

    fun part1(input: String): Int {
        return input.elves.maxOf { it.foods.sumOf(Day01.Food::calories) }
    }

    fun part2(input: String): Int {
        return input.elves.map { it.foods.sumOf(Day01.Food::calories) }
            .sortedDescending()
            .take(3)
            .sum()
    }

    val testInput = readInputText("/day%02d/input_test".format(day))
    val input = readInputText("/day%02d/input".format(day))

    check(part1(testInput) == 24000)
    println("part1 answer is ${part1(input)}")

    check(part2(testInput) == 45000)
    println("part2 answer is ${part2(input)}")
}

object Day01 {

    data class Elf(
        val foods: List<Food>,
    )

    data class Food(
        val calories: Int,
    )
}

private val String.elves: List<Day01.Elf>
    get() = split("\n\n")
        .map(String::foods)
        .map(Day01::Elf)

private val String.foods: List<Day01.Food>
    get() = split("\n")
        .map(String::toInt)
        .map(Day01::Food)
