package aoc.day5

import aoc.getResourceUri
import aoc.equalsOppositeCase
import java.io.File

fun main() {
    val input = File(getResourceUri("input5.txt")).bufferedReader().readLines()

    println("Result for part 1: ${react(input[0]).length}")

    val (letter, size) = CharRange('a', 'z')
            .map { Pair(it, react(input[0].filterNot { c -> c.equals(it, true) }).length) }
            .minBy { it.second }!!

    println("Result for part 2: $letter => $size")
}

fun react(input: String): String {
    return input.toCharArray().fold(listOf<Char>(), { acc, c ->
        when {
            acc.isNotEmpty() && c.equalsOppositeCase(acc.last()) -> acc.dropLast(1)
            else -> acc.plus(c)
        }
    }).joinToString(separator = "", postfix = "")
}
