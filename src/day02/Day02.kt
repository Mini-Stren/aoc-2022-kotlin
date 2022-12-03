package day02

import readInputLines

fun main() {

    val day = 2

    fun part1(input: List<String>): Int {
        return Day02_part1.gameRounds(input).sumOf(Day02.GameRound::score)
    }

    fun part2(input: List<String>): Int {
        return Day02_part2.gameRounds(input).sumOf(Day02.GameRound::score)
    }

    val testInput = readInputLines("/day%02d/input_test".format(day))
    val input = readInputLines("/day%02d/input".format(day))

    check(part1(testInput) == 15)
    println("part1 answer is ${part1(input)}")

    check(part2(testInput) == 12)
    println("part2 answer is ${part2(input)}")
}

object Day02 {

    data class GameRound(
        val opponentShape: HandShape,
        val playerShape: HandShape,
        val gameResult: GameResult,
    ) {
        companion object
    }

    enum class HandShape {
        Rock, Paper, Scissors;

        val score: Int
            get() = when (this) {
                Rock -> 1
                Paper -> 2
                Scissors -> 3
            }

        companion object {

            fun fromOpponent(value: Char): HandShape = when (value) {
                'A' -> Rock
                'B' -> Paper
                'C' -> Scissors
                else -> throw IllegalArgumentException("Unknown opponent value '$value'")
            }
        }
    }

    enum class GameResult {
        Draw, PlayerWins, PlayerLose;

        val score: Int
            get() = when (this) {
                Draw -> 3
                PlayerWins -> 6
                PlayerLose -> 0
            }

        companion object
    }
}

private val Day02.GameRound.score: Int
    get() = playerShape.score + gameResult.score
