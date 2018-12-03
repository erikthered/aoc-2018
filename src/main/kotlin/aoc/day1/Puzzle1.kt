package aoc.day1

import aoc.getResourceUri
import java.io.File

fun main(args: Array<String>) {
    var freq = 0
    File(getResourceUri("input1.txt"))
            .bufferedReader().lineSequence().forEach {
                when {
                    it.startsWith("-") -> freq -= it.substring(1).toInt()
                    it.startsWith("+") -> freq += it.substring(1).toInt()
                }
                System.out.println(freq)
            }
}