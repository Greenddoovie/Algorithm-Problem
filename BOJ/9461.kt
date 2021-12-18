package BOJ

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val testNum = br.readLine().toInt()
    val arr: ArrayList<Long> = arrayListOf()
    arr.add(1)
    arr.add(1)
    arr.add(1)
    arr.add(2)
    arr.add(2)

    for (i in 5..100) {
        arr.add(arr[i-1] + arr[i-5])
    }
    repeat(testNum) {
        println(arr[br.readLine().toInt()-1])
    }
}