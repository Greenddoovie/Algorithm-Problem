package `Algorithm-Problem`.BOJ

fun main() {
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    var max = 0
    Array(N) { br.readLine().toInt() }.withIndex().sortedBy { it.value }.forEachIndexed { idx, it ->
        max = maxOf(it.index - idx, max)
    }
    println(max + 1)
}
