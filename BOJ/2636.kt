import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

class IO2636 {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val bw = BufferedWriter(OutputStreamWriter(System.out))

    fun getNM() = br.readLine().split(" ").map { it.toInt() }
    fun getRows() = br.readLine().split(" ")
    fun write(message: String) = bw.write(message)
    fun flush() = bw.flush()
    fun close() = bw.close()
}

fun main() {
    val io = IO2636()
    val (height, width) = io.getNM()
    val graph = Array(height) { arrayListOf<Short>() }
    var cheeseCount = 0
    repeat(height) {
        graph[it].addAll(io.getRows().map { e ->
            if (e == "1") {
                cheeseCount++
                (1)
            }
            else (2)
        })
    }

    val checkInside = { point: Point -> point.x in (0 until width) && point.y in (0 until height) }

    val dx = listOf(-1,1,0,0)
    val dy = listOf(0,0,-1,1)

    fun markOuter() {
        val q: Queue<Point> = LinkedList()
        q.add(Point(0,0))
        var visited = getVisited(height, width)
        while (q.isNotEmpty()) {
            val point = q.poll()
            val element = graph[point.y][point.x]
            if (element == (2).toShort()) {
                graph[point.y][point.x] = (0)
            }
            for (idx in 0..3) {
                val newPoint = Point(point.y + dy[idx], point.x + dx[idx])
                if (checkInside(newPoint) && !visited[newPoint.y][newPoint.x] && graph[newPoint.y][newPoint.x] != (1).toShort()) {
                    q.add(newPoint)
                    visited[newPoint.y][newPoint.x] = true
                }
            }
        } // 공기 외부, 공기 내부, 치즈 구분 완료
    }

    fun meltCheese(): Int {
        val meltingList = mutableListOf<Point>()
        var melting = 0
        for (row in 0 until height) {
            for (col in 0 until width) {
                if (graph[row][col] == (1).toShort()) {
                    for (idx in 0..3) {
                        val newPoint = Point(row + dy[idx], col + dx[idx])
                        if (checkInside(newPoint) && graph[newPoint.y][newPoint.x] == (0).toShort()) {
                            meltingList.add(Point(row, col))
                            melting++
                            break
                        }
                    }
                }
            }
        }
        meltingList.forEach {
            graph[it.y][it.x] = (0)
        }
        return melting
    }

    var hour = 0
    val ansList = mutableListOf(hour to cheeseCount)
    while (cheeseCount > 0) {
        markOuter()
        val count = meltCheese()
        hour++
        cheeseCount -= count
        ansList.add(hour to cheeseCount)
    }

    io.write(ansList[ansList.lastIndex].first.toString() + "\n")
    io.write(ansList[ansList.lastIndex-1].second.toString())
    io.flush()
    io.close()
}

fun getVisited(height: Int, width: Int): Array<Array<Boolean>> {
    return Array(height) { Array(width) { false } }
}

data class Point(val y: Int, val x: Int)
