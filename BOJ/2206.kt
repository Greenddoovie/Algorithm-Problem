package `Algorithm-Problem`.BOJ

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

class IO2206 {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val bw = BufferedWriter(OutputStreamWriter(System.out))

    fun getNumList() = br.readLine().split(" ").map { it.toInt() }
    fun getRow() = br.readLine()!!
    fun flush() = bw.flush()
    fun close() = bw.close()
    fun write(message: String) = bw.write(message)
}

fun main() {
    val io = IO2206()
    val (N, M) = io.getNumList()
    val graph = mutableListOf<Array<Int>>()
    repeat(N) {
        graph.add(io.getRow().map { it.toString().toInt() }.toTypedArray())
    }
    val visited = mutableListOf<Array<Array<Boolean>>>().also { list ->
        repeat(N) { list.add(Array(M) { Array(2) { false } }) }
    }

    var ans = Int.MAX_VALUE

    data class Info (val row: Int, val col: Int, val dis: Int, val destroyed: Boolean)

    val queue: Queue<Info> = LinkedList()
    queue.add(Info(0,0,1, false))
    visited[0][0][0] = true
    while (queue.isNotEmpty()) {
        val info = queue.poll()
        val (row, col, dis, destroyed) = info
        if (row == N-1 && col == M-1) {
            ans = dis
            break
        }
        val dy = listOf(-1,1,0,0) // 상하좌우
        val dx = listOf(0,0,-1,1)
        for (idx in (0..3)) {
            val newY = row + dy[idx]
            val newX = col + dx[idx]
            if (newY < 0 || newY > N - 1 || newX < 0 || newX > M - 1) {
                continue
            }
            if (graph[newY][newX] == 0) {
                if (destroyed && !visited[newY][newX][1] && !visited[newY][newX][0]) {
                    queue.add(Info(newY, newX, dis+1, destroyed))
                    visited[newY][newX][1] = true
                } else if (!destroyed && !visited[newY][newX][0]) {
                    queue.add(Info(newY, newX, dis+1, destroyed))
                    visited[newY][newX][0] = true
                }
            } else if (graph[newY][newX] == 1) {
                if (!destroyed) {
                    queue.add(Info(newY, newX, dis+1, !destroyed))
                    visited[newY][newX][1] = true
                }
            }
        }
    }
    if (ans == Int.MAX_VALUE) ans = -1
    io.write(ans.toString())
    io.flush()
    io.close()
}