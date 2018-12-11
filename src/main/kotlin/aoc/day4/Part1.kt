package aoc.day4

import aoc.getResourceUri
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val timeTaken = measureTimeMillis {
        val input = File(getResourceUri("input4.txt")).bufferedReader().readLines()

        val regex = """\[(.*)\]\s(.*)""".toRegex()
        val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

        val shiftChangeRegex = """Guard #(\d+) begins shift""".toRegex()

        val minutesAsleep = mutableMapOf<Int, List<Int>>()

        val eventLog = input.map {
            val (ts, text) = regex.find(it)!!.destructured
            PatrolEvent(LocalDateTime.parse(ts, dateFormat), text)
        }
                .sortedBy { it.timestamp }

        var currentId: Int = 0
        var startAsleep: Int = 0
        var endAsleep: Int = 0

        eventLog.forEach {
            when {
                shiftChangeRegex.matches(it.event) -> {
                    currentId = shiftChangeRegex.find(it.event)!!.groupValues[1].toInt()
                }
                it.event == "falls asleep" -> {
                    startAsleep = it.timestamp.minute
                }
                it.event == "wakes up" -> {
                    endAsleep = it.timestamp.minute

                    val minutes = startAsleep until endAsleep

                    if (minutesAsleep.containsKey(currentId)) {
                        minutesAsleep[currentId] = minutesAsleep[currentId]!!.plus(minutes)
                    } else {
                        minutesAsleep[currentId] = minutes.toList()
                    }
                }
            }
        }

        val (id, minutes) = minutesAsleep.maxBy { it.value.size }!!

        println("$id: ${minutes.size}")

        val (minute, occurrences) = minutes.groupingBy { it }.eachCount().maxBy { it.value }!!

        println("Minute: $minute, $occurrences times")

        println("Checksum 1: ${id * minute}")


        val (id2, mins2) = minutesAsleep.maxBy { entry ->
            //find minute with most occurrences for each record
            entry.value.groupingBy { it }.eachCount().maxBy { it.value }!!.value
        }!!

        val min2 = mins2.groupingBy { it }.eachCount().maxBy { it.value }!!.key

        println("Checksum 2: ${id2 * min2}")

    }
    println("time taken: ${timeTaken}ms")
}

data class PatrolEvent(val timestamp: LocalDateTime, val event: String)