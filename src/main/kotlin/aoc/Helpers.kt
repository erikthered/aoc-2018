package aoc

import java.net.URI

fun getResourceUri(fileName: String): URI = {}.javaClass.classLoader.getResource(fileName).toURI()