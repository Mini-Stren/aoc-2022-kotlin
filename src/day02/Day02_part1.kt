package day02

@Suppress("ClassName")
object Day02_part1 {

    fun gameRounds(input: List<String>): List<Day02.GameRound> {
        return input.map(String::toCharArray).map {
            val opponentShape = Day02.HandShape.fromOpponent(it.first())
            val playerShape = Day02.HandShape.fromPlayer(it.last())
            val gameResult = Day02.GameResult.calculate(opponentShape, playerShape)
            Day02.GameRound(opponentShape, playerShape, gameResult)
        }
    }
}

private fun Day02.HandShape.Companion.fromPlayer(value: Char): Day02.HandShape = when (value) {
    'X' -> Day02.HandShape.Rock
    'Y' -> Day02.HandShape.Paper
    'Z' -> Day02.HandShape.Scissors
    else -> throw IllegalArgumentException("Unknown player value '$value'")
}

private fun Day02.GameResult.Companion.calculate(
    opponentShape: Day02.HandShape,
    playerShape: Day02.HandShape,
): Day02.GameResult = when {
    playerShape == opponentShape -> Day02.GameResult.Draw

    playerShape == Day02.HandShape.Rock && opponentShape == Day02.HandShape.Scissors ||
            playerShape == Day02.HandShape.Paper && opponentShape == Day02.HandShape.Rock ||
            playerShape == Day02.HandShape.Scissors && opponentShape == Day02.HandShape.Paper
    -> Day02.GameResult.PlayerWins

    else -> Day02.GameResult.PlayerLose
}
