package day02

@Suppress("ClassName")
object Day02_part2 {

    fun gameRounds(input: List<String>): List<Day02.GameRound> {
        return input.map(String::toCharArray).map {
            val opponentShape = Day02.HandShape.fromOpponent(it.first())
            val gameResult = Day02.GameResult.fromInput(it.last())
            val playerShape = Day02.HandShape.calculate(opponentShape, gameResult)
            Day02.GameRound(opponentShape, playerShape, gameResult)
        }
    }
}

private fun Day02.GameResult.Companion.fromInput(value: Char): Day02.GameResult = when (value) {
    'X' -> Day02.GameResult.PlayerLose
    'Y' -> Day02.GameResult.Draw
    'Z' -> Day02.GameResult.PlayerWins
    else -> throw IllegalArgumentException("Unknown game result value '$value'")
}

private fun Day02.HandShape.Companion.calculate(
    opponentShape: Day02.HandShape,
    gameResult: Day02.GameResult,
): Day02.HandShape = when (gameResult) {
    Day02.GameResult.Draw -> opponentShape

    Day02.GameResult.PlayerWins -> when (opponentShape) {
        Day02.HandShape.Rock -> Day02.HandShape.Paper
        Day02.HandShape.Paper -> Day02.HandShape.Scissors
        Day02.HandShape.Scissors -> Day02.HandShape.Rock
    }

    Day02.GameResult.PlayerLose -> when (opponentShape) {
        Day02.HandShape.Rock -> Day02.HandShape.Scissors
        Day02.HandShape.Paper -> Day02.HandShape.Rock
        Day02.HandShape.Scissors -> Day02.HandShape.Paper
    }
}
