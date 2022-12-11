package day05

import readInputLines
import java.util.*

fun main() {

    val day = 5

    fun part1(input: List<String>): String {
        val cratesStacks = input.cratesStacks
        val cratesMoves = input.cratesMoves

        cratesMoves.forEach { (count, from, to) ->
            repeat(count) {
                val crate = cratesStacks[from - 1].pop()
                cratesStacks[to - 1].push(crate)
            }
        }

        return cratesStacks.topCrates
    }

    fun part2(input: List<String>): String {
        val cratesStacks = input.cratesStacks
        val cratesMoves = input.cratesMoves

        cratesMoves.forEach { (count, from, to) ->
            (0 until count)
                .map { cratesStacks[from - 1].pop() }
                .reversed()
                .forEach { cratesStacks[to - 1].push(it) }
        }

        return cratesStacks.topCrates
    }

    val testInput = readInputLines("/day%02d/input_test".format(day))
    val input = readInputLines("/day%02d/input".format(day))

    check(part1(testInput) == "CMZ")
    println("part1 answer is ${part1(input)}")

    check(part2(testInput) == "MCD")
    println("part2 answer is ${part2(input)}")
}

private val List<String>.cratesStacks: List<Stack<Char>>
    get() {
        val inputSplitLineIndex = indexOf("")
        val cratesStacksCount = get(inputSplitLineIndex - 1).substringAfterLast(' ').toInt()
        val cratesLineRegex = "(\\D{1,3})\\s?".toRegex()

        val crates = subList(0, inputSplitLineIndex - 1).map { line ->
            cratesLineRegex.findAll(line, 0)
                .map { it.value.trim() }
                .map { it.getOrNull(1) }
                .toList()
                .let { cratesStack ->
                    cratesStack.takeIf { it.size == cratesStacksCount }
                        ?: (cratesStack + (cratesStack.size until cratesStacksCount).map { null })
                }
        }

        return (0 until cratesStacksCount).map { stackIndex ->
            Stack<Char>().apply {
                crates.reversed()
                    .mapNotNull { it[stackIndex] }
                    .forEach(::push)
            }
        }
    }

private val List<String>.cratesMoves: List<Triple<Int, Int, Int>>
    get() {
        val inputSplitLineIndex = indexOf("")
        val movesLineRegex = "move (\\d+) from (\\d+) to (\\d+)".toRegex()

        return subList(inputSplitLineIndex + 1, size).map { line ->
            val movesGroup = movesLineRegex.matchAt(line, 0)
                ?.groupValues?.drop(1)
                ?: emptyList()
            Triple(movesGroup[0].toInt(), movesGroup[1].toInt(), movesGroup[2].toInt())
        }
    }

private val List<Stack<Char>>.topCrates: String
    get() = map(Stack<Char>::peek).joinToString(separator = "")
