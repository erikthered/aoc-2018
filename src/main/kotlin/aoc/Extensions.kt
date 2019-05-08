package aoc

fun Char.equalsOppositeCase(other: Char): Boolean {
    return this.equals(other, ignoreCase = true) && this.isUpperCase() != other.isUpperCase()
}