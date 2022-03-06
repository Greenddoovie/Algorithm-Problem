package `Algorithm-Problem`.BOJ

import kotlin.math.hypot
import java.util.*

class IO1774 {
    private val br = System.`in`.bufferedReader()
    private val bw = System.out.bufferedWriter()

    fun close() = bw.close()
    fun write(message: String) = bw.write(message)
    fun getRow() = br.readLine().split(" ").map{ it.toInt() }
}

fun main() {
    val io = IO1774()
    val (N, M) = io.getRow()

    fun getArray(num: Int): Array<List<Int>> {
        return Array(num) {
            io.getRow()
        }
    }

    val positionArr = getArray(N)
    val distanceArr = Array(N) { Array(N) { Double.MAX_VALUE } }

    fun getDistance(p1: List<Int>, p2: List<Int>): Double {
        val (x1, y1) = p1
        val (x2, y2) = p2
        return hypot((x1 - x2).toDouble(), (y1 - y2).toDouble())
    }

    for (i in 0 until N) {
        val p1 = positionArr[i]
        for (j in i+1 until N) {
            val p2 = positionArr[j]
            val dis = getDistance(p1, p2)
            distanceArr[i][j] = dis
            distanceArr[j][i] = dis
        }
    }

    val visited = Array(N) { false }

    repeat(M) {
        val (p1, p2) = io.getRow()
        distanceArr[p1-1][p2-1] = 0.0
        distanceArr[p2-1][p1-1] = 0.0
    }

    data class Edge(val source: Int, val dest: Int, val cost: Double)
    val pq = PriorityQueue<Edge>(compareBy { it.cost })
    for(row in 1 until N) {
        pq.add(Edge(0, row, distanceArr[0][row]))
    }
    var answer: Double = 0.0
    var edge = 0
    visited[0] = true
    while (edge < N - 1) {
        val e = pq.poll()
        val (sour, dest, cost) = e

        if (visited[dest]) continue
        visited[dest] = true
        answer += cost
        edge++

        if (cost == Double.MAX_VALUE) continue

        for (row in 0 until N) {
            if (dest == row) continue
            pq.add(Edge(dest, row, distanceArr[dest][row]))
        }
    }
    io.write(String.format("%.2f", answer))
    io.close()
}