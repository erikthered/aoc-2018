package aoc.day5

import aoc.getResourceUri
import aoc.equalsOppositeCase
import java.io.File

fun main() {
    val input = File(getResourceUri("input5.txt")).bufferedReader().readLines()

    println(react(input[0]).length)
}

fun react(input: String): String {
    return input.toCharArray().fold(listOf<Char>(), { acc, c ->
        when {
            acc.isNotEmpty() && c.equalsOppositeCase(acc.last()) -> acc.dropLast(1)
            else -> acc.plus(c)
        }
    }).joinToString(separator = "", postfix = "")
}
