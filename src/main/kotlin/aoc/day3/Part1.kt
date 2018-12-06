package aoc.day3

import aoc.getResourceUri
import java.io.File
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val timeTaken = measureTimeMillis {
        val input = File(getResourceUri("input3.txt")).bufferedReader().readLines()

        val claims = input.map { row ->
            val (id, point, size) = row.split(" @ ", ": ")
            val (x, y) = point.split(",").map { it.toInt() }
            val (w, h) = size.split("x").map { it.toInt() }
            Claim(id, Pair(x, y), Pair(w, h))
        }

        val occupiedPoints = claims.flatMap { claim ->
            println(claim)

            val points = mutableListOf<Pair<Int, Int>>()

            for (y in claim.start.second..(claim.start.second + claim.dimensions.second-1)) {
                for (x in claim.start.first..(claim.start.first + claim.dimensions.first-1)) {
                    points.add(x to y)
                }
            }

            points
        }.groupingBy { it }.eachCount()

        println(occupiedPoints.filterValues { it > 1 }.count())
    }
    println("time taken: ${timeTaken}ms")
}

data class Claim(val id: String, val start: Pair<Int, Int>, val dimensions: Pair<Int, Int>)