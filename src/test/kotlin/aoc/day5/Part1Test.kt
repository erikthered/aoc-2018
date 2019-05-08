package aoc.day5

import org.junit.Test
import kotlin.test.assertEquals


class Part1Test {

    @Test
    fun testCollapse() {
        val input = "dabAcCaCBAcCcaDA"
        // dabAcCaCBAcCcaDA  The first 'cC' is removed.
        // dabAaCBAcCcaDA    This creates 'Aa', which is removed.
        // dabCBAcCcaDA      Either 'cC' or 'Cc' are removed (the result is the same).
        // dabCBAcaDA        No further actions can be taken.
        assertEquals("dabCBAcaDA", react(input))
    }

}