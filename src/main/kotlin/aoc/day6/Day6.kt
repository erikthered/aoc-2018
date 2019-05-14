package aoc.day6

import aoc.getResourceUri
import java.io.File
import kotlin.system.measureTimeMillis

typealias Point = Pair<Int, Int>

fun manhattanDistance(point1: Pair<Int, Int>, point2: Pair<Int, Int>): Int =
        Math.abs(point1.first - point2.first) + Math.abs(point1.second - point2.second)

fun Point.taxicabDistance(other: Point): Int = manhattanDistance(this, other)

fun parsePointString(input: String): Point {
    val (a, b) = input.split(", ")
    return a.toInt() to b.toInt()
}

fun findLargestFiniteArea(points: List<Point>): Int {

    val allCoordinates: List<Point> = generateCoordinates(points)

    val areaSizes = mutableMapOf<Point, Int>()

    allCoordinates.forEach { pt ->
        val nearestPoints = points.filter { candidate -> candidate.taxicabDistance(pt) == points.map { it.taxicabDistance(pt) }.min() }

        when {
            nearestPoints.size == 1 -> areaSizes[nearestPoints.first()] = areaSizes[nearestPoints.first()]?.plus(1)
                    ?: 1
        }
    }

    return areaSizes.map { it.value }.max()!!
}

fun findAreaUnderMinTotalDistance(points: List<Point>, threshold: Int): Int {
    val allCoordinates = generateCoordinates(points)

    return allCoordinates.map { coord ->
        points.map { pt -> pt.taxicabDistance(coord) }
                .reduce { acc, dist -> acc + dist }
    }.filter { it < threshold }.size
}

private fun generateCoordinates(points: List<Point>): List<Point> {
    val xMin = points.map { it.first }.min()!!
    val xMax = points.map { it.first }.max()!!
    val yMin = points.map { it.second }.min()!!
    val yMax = points.map { it.second }.max()!!

    return xMin.rangeTo(xMax).flatMap { x -> yMin.rangeTo(yMax).map { y -> Point(x, y) } }
}

fun main() {
    val input = File(getResourceUri("input6.txt")).bufferedReader().readLines()

    measureTimeMillis { println("${findLargestFiniteArea(input.map(::parsePointString))}") }
            .apply { println("Part one took ${this}ms") }

    measureTimeMillis { println("${findAreaUnderMinTotalDistance(input.map(::parsePointString), 10000)}") }
            .apply { println("Part two took ${this}ms") }
}