package aoc.day1

import aoc.getResourceUri
import java.io.File

fun main(args: Array<String>) {
    var freq = 0
    File(getResourceUri("input1.txt"))
            .bufferedReader().lineSequence().forEach {
                freq += it.toInt()
            }
    println(freq)
}