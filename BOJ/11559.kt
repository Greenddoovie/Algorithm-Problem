import java.util.Queue
import java.util.LinkedList

class IO11559 {
    private val br = System.`in`.bufferedReader()
    private val bw = System.out.bufferedWriter()

    fun getRow() = br.readLine().toList().map { it.toString() }.toMutableList()
    fun close() = bw.close()
    fun write(message: String) = bw.write(message)
}

fun main() {
    var answer = 0
    val io = IO11559()
    val graph = Array(12){ mutableListOf<String>()}
    repeat(12) {
        graph[it] = io.getRow()
    }

    val colGraph = Array(6) { mutableListOf<String>() }
    repeat(6) {
        val tmpList = mutableListOf<String>()
        for (idx in 11 downTo 0) {
            tmpList.add(graph[idx][it])
        }
        colGraph[it] = tmpList
    }

    val dxy = listOf( -1 to 0, 1 to 0, 0 to -1, 0 to 1)

    data class Point(val col: Int, val row: Int)

    fun findErasableArea(): MutableList<MutableSet<Point>> {
        val visited = Array(6) { Array(12) { false } }
        val eraseAreaList = mutableListOf<MutableSet<Point>>()
        for (col in (0..5)) {
            for (row in (0..11)) {
                if (visited[col][row]) continue
                if (colGraph[col][row] ==".") {
                    visited[col][row] = true
                    continue
                }
                val q: Queue<Point> = LinkedList()
                val tmpEraser = mutableSetOf<Point>()

                q.add(Point(col, row))
                tmpEraser.add(Point(col, row))

                while (q.isNotEmpty()) {
                    val (curCol, curRow) = q.poll()
                    if (visited[curCol][curRow]) continue
                    visited[curCol][curRow] = true
                    for (idx in 0..3) {
                        val (cy, cx) = dxy[idx]
                        val nx = cx + curRow
                        val ny = cy + curCol
                        if (nx < 0 || nx > 11 || ny < 0 || ny > 5) continue
                        if (colGraph[ny][nx] == colGraph[col][row] && !visited[ny][nx]) {
                            Point(ny,nx).also {
                                q.add(it)
                                tmpEraser.add(it)
                            }
                        }
                    }
                }
                if (tmpEraser.size > 3) {
                    eraseAreaList.add(tmpEraser)
                }
            }
        }
        return eraseAreaList
    }

    fun remove(area: MutableList<MutableSet<Point>>) {
        area.forEach { each ->
            each.forEach { it ->
                val (col, row) = it
                colGraph[col][row] = "."
            }
        }
    }

    fun fillEmpty() {
        for (col in 0..5) {
            val tmpCol = colGraph[col].filter { it != "." }.toMutableList()
            for (r in 1..(12-tmpCol.size)) {
                tmpCol.add(".")
            }
            colGraph[col] = tmpCol
        }
    }

    while (true) {
        val erasableAreaList = findErasableArea()
        if (erasableAreaList.isEmpty()) {
            break
        }
        remove(erasableAreaList)
        fillEmpty()
        answer++
    }

    io.write(answer.toString())
    io.close()

}