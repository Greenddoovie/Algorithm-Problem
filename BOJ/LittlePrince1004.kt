package BOJ

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.pow

val br = BufferedReader(InputStreamReader(System.`in`))
fun main() {
    val testNum = br.readLine().trim().toInt()
    val ansList = mutableListOf<Int>()
    for (i in 0 until testNum) {
        val (x1,y1, x2,y2) = br.readLine().trim().split(" ")
        val points = listOf(Point(x1.toInt(),y1.toInt()), Point(x2.toInt(),y2.toInt()))
        val plantNum = br.readLine().trim().toInt()
        val plantList = mutableListOf<Plant>()
        repeat(plantNum) {
            val (cx, cy, r) = br.readLine().trim().split(" ")
            plantList.add(Plant(cx.toInt(), cy.toInt(), r.toInt()))
        }
        var ans = 0
        plantList.forEach { plant ->
            var meet = 0
            points.forEach { point ->
                meet += checkInAndOut(point, plant)
            }
            if (meet == 1) {
                ans++
            }
        }
        ansList.add(ans)
    }
    ansList.forEach {
        println(it)
    }
}

fun checkInAndOut(point: Point, plant: Plant): Int {
    val left = (point.x - plant.cx).toFloat().pow(2) + (point.y - plant.cy).toFloat().pow(2)
    val right = plant.r.toFloat().pow(2)
    return if (left <= right) 1 else 0
}

data class Plant(val cx: Int, val cy: Int, val r: Int)
data class Point(val x:Int, val y: Int)