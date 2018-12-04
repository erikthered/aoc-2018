package aoc.day2

import aoc.getResourceUri
import java.io.File
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val timeTaken = measureTimeMillis {
        val input = File(getResourceUri("input2.txt")).bufferedReader().readLines()

        for ((idx, value) in input.withIndex()) {
            for (value2 in input.slice(idx + 1..input.lastIndex)) {
                if (value.hammingDistance(value2) == 1) {
                    println(value.intersection(value2))
                    break
                }
            }
        }
    }
    println("time taken: ${timeTaken}ms")
}

fun String.hammingDistance(other: String) = this.filterIndexed { index, c -> c != other[index] }.length

fun String.intersection(other: String) = this.filterIndexed { index, c -> c == other[index]}