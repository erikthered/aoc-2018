package aoc.day2

import aoc.getResourceUri
import java.io.File
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val timeTaken = measureTimeMillis {
        val input = File(getResourceUri("input2.txt")).bufferedReader().readLines()

        // Filter lines that have exactly two occurrences of any single character
        val exactly2 = input.asSequence().map { line -> line.groupingBy { it }.eachCount() }
                .filter { charMap -> charMap.filterValues { it == 2 }.isNotEmpty() }
                .count()


        // Filter lines that have exactly 3 occurrences of any single character
        val exactly3 = input.asSequence().map { line -> line.groupingBy { it }.eachCount() }
                .filter { charMap -> charMap.filterValues { it == 3 }.isNotEmpty() }
                .count()

        println("Checksum: ${exactly2 * exactly3}")
    }
    println("time taken: ${timeTaken}ms")
}