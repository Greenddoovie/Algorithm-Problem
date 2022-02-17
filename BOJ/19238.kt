package `Algorithm-Problem`.BOJ

import java.util.*

// 19238번 스타트택시

fun main() {
    val br = System.`in`.bufferedReader()
    val (N, M, fuel) = br.readLine().split(" ").map { it.toInt() }
    var tFuel = fuel
    val graph = Array(N) { br.readLine().split(" ").map { it.toInt() }.toTypedArray() }

    var (tRow, tCol) = br.readLine().split(" ").map { it.toInt() - 1 }

    var list = mutableListOf<Pair<Passenger, Destination>>()
    repeat(M) {
        val (pRow, pCol, dRow, dCol) = br.readLine().split(" ").map { it.toInt() -1 }
        list.add(Passenger(pCol, pRow) to Destination(dCol, dRow))
    }
    list = list.sortedWith(compareBy({it.first.row}, {it.first.col})).toMutableList()
    val visited = Array(N) { Array(N) { false } }
    fun initVisited() {
        (0 until N). forEach { row ->
            (0 until N).forEach { col ->
                visited[row][col] = false
            }
        }
    }

    val dx = listOf(-1,1,0,0) // 좌우
    val dy = listOf(0,0,-1,1) // 상하
    val range = 0..3
    fun findDistanceFromTaxi(): Array<Array<Int>> {
        val q: Queue<Point> = LinkedList()
        val disArr = Array(N) { Array(N) { Int.MAX_VALUE} }
        disArr[tRow][tCol] = 0
        visited[tRow][tCol] = true

        q.add(Point(tCol, tRow, 0))
        while (q.isNotEmpty()) {
            val (cCol, cRow, distance) = q.poll()
            range.forEach {
                val nx = cCol + dx[it]
                val ny = cRow + dy[it]
                if (nx > -1 && nx < N && ny > -1 && ny < N && graph[ny][nx] == 0 && !visited[ny][nx]) {
                    disArr[ny][nx] = distance + 1
                    q.add(Point(nx, ny, distance + 1))
                    visited[ny][nx] = true
                }
            }
        }
        initVisited()
        return disArr
    }

    val targetList = Array(M) { 0 }  // idx, 거리, 하나로 계속 사용 및 사용 시 거리를 Int.MAX_VALUE로 변경
    var flag = true
    while (list.isNotEmpty()) {
        var minDis = Int.MAX_VALUE
        var disArr = findDistanceFromTaxi()
        list.forEachIndexed { idx, it ->
            val (pCol, pRow) = it.first
            disArr[pRow][pCol].also {
                targetList[idx] = it
                minDis = minOf(it, minDis)
            }
        }
        val idx = targetList.indexOfFirst { it == minDis }
        if (idx == -1 || minDis >= tFuel) {
            flag = false
            break
        } else {
            tFuel -= minDis
            tCol = list[idx].first.col
            tRow = list[idx].first.row
        }

        disArr = findDistanceFromTaxi()
        val disDest = disArr[list[idx].second.row][list[idx].second.col]
        if (disDest <= tFuel) {
            tFuel += disDest
            tCol = list[idx].second.col
            tRow = list[idx].second.row
        } else {
            flag = false
            break
        }
        list.removeAt(idx)
    }

    if (flag) println(tFuel) else println(-1)
}

data class Passenger(val col: Int, val row: Int)
data class Destination(val col: Int, val row: Int)
data class Point(val col: Int, val row: Int, val distance: Int)