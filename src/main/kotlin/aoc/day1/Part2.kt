package aoc.day1

import aoc.getResourceUri
import java.io.File
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val input = File(getResourceUri("input1.txt")).bufferedReader()
            .lineSequence().map { it.toInt() }.toList()

    val changeSeq = generateSequence { input.asIterable() }.flatten()

    var currFreq = 0
    val freqs = mutableSetOf<Int>()
    var iters = 0

    val timeTaken = measureTimeMillis {
        changeSeq
                .map {
                    currFreq += it
                    currFreq
                }
                .takeWhile { !freqs.contains(it) }
                .forEach {
                    freqs.add(it)
                    iters++
                }

    }

    println("first repeated freq: $currFreq")
    println("iterations: $iters")
    println("time taken: ${timeTaken}ms")
}