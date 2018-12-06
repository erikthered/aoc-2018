package aoc.day3

import aoc.getResourceUri
import java.io.File
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val timeTaken = measureTimeMillis {
        val input = File(getResourceUri("input3.txt")).bufferedReader().readLines()

        // Part 1

        val claims = input.map { row ->
            val (id, point, size) = row.split(" @ ", ": ")
            val (x, y) = point.split(",").map { it.toInt() }
            val (w, h) = size.split("x").map { it.toInt() }
            Claim(id, Pair(x, y), Pair(w, h))
        }

        val occupiedPoints = claims.flatMap { claim -> producePoints(claim.start, claim.dimensions)}.groupingBy { it }.eachCount()

        val conflicts = occupiedPoints.filterValues { it > 1 }

        println(conflicts.count())

        // Part 2

        val goodClaim = claims.first { claim ->
            producePoints(claim.start, claim.dimensions).none { conflicts.containsKey(it) }
        }

        println(goodClaim)
    }
    println("time taken: ${timeTaken}ms")
}

data class Claim(val id: String, val start: Pair<Int, Int>, val dimensions: Pair<Int, Int>)

fun producePoints(point: Pair<Int, Int>, dimensions: Pair<Int, Int>): List<Pair<Int, Int>> {
    val points = mutableListOf<Pair<Int, Int>>()

    for (y in point.second..(point.second + dimensions.second-1)) {
        for (x in point.first..(point.first + dimensions.first-1)) {
            points.add(x to y)
        }
    }

    return points
}