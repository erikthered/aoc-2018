package aoc.day6

import kotlin.test.Test
import kotlin.test.assertEquals

class Day6Test {

    val testInput = """
        1, 1
        1, 6
        8, 3
        3, 4
        5, 5
        8, 9
    """.trimIndent().lines()

    @Test
    fun testManhattanDistance() {
        assertEquals(7, manhattanDistance(0 to 0, 5 to 2))
        assertEquals(10, manhattanDistance(5 to 5, 3 to 13))
    }

    @Test
    fun testFindLargestFiniteArea() {
        val points = testInput.map(::parsePointString)
        assertEquals(17, findLargestFiniteArea(points))
    }

    @Test
    fun testFindAreaUnderMinTotalDistance() {
        val points = testInput.map(::parsePointString)
        assertEquals(16, findAreaUnderMinTotalDistance(points, 32))
    }

    @Test
    fun testPointParsing() {
        assertEquals(
                listOf(1 to 1, 1 to 6, 8 to 3, 3 to 4, 5 to 5, 8 to 9),
                testInput.map(::parsePointString)
        )
    }
}