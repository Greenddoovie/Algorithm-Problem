package BOJ

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs

fun main() {
    val io = StandardIO()
    val testNum = io.getIntFromBr()
    val ansList = mutableListOf<String>()
    repeat(testNum) {
        val cvsNum = io.getIntFromBr()
        val house = io.getPosFromBr()
        val cvsList = mutableListOf<Pos>().apply {
            repeat(cvsNum) { add(io.getPosFromBr()) }
        }
        val festival = io.getPosFromBr()
        var beer = 20
        var success = false
        val visited: Array<Boolean> = Array(cvsNum) { false }
        val queue = mutableListOf(house)
        while (queue.isNotEmpty()) {
            val now = queue.removeFirst()
            if (getDistance(now, festival) <= beer * 50)  {
                success = true
                break
            }
            for (i in cvsList.indices) {
                if (!visited[i] && getDistance(now, cvsList[i]) <= beer * 50) {
                    queue.add(cvsList[i])
                    visited[i] = true
                }
            }
        }
        ansList.add(if (success) "happy" else "sad")
    }
    ansList.forEach { println(it) }
}

fun getDistance(pos1: Pos, pos2: Pos): Int {
    return abs(pos2.x - pos1.x) + abs(pos2.y - pos1.y)
}

class StandardIO {
    private val br = BufferedReader(InputStreamReader(System.`in`))

    fun getIntFromBr(): Int = br.readLine().toInt()

    fun getPosFromBr(): Pos {
        val (x,y) = br.readLine().split(" ").map{ it.toInt() }
        return Pos(x, y)
    }
}
