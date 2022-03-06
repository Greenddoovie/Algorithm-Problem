package `Algorithm-Problem`.BOJ

fun main() {
    val br = System.`in`.bufferedReader()
    data class PointRange(val x: Int, val y: Int)
    val points = mutableListOf<PointRange>()

    repeat(br.readLine().toInt()) {
        br.readLine().split(" ").map { it.toInt() }.run {
            points.add(PointRange(component1(), component2()))
        }
    }

    points.sortWith(compareBy({it.x}, {it.y}))

    var index = 1
    var start = points.first().x
    var end = points.first().y
    var length = 0
    while (index < points.size) {
        val (x, y) = points[index]
        if (x > end) {
            length += end - start
            end = y
            start = x
        } else if ( x > start && y > end) {
            end = y
        }
        index++
    }
    length += end - start
    println(length)
}