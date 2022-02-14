package `Algorithm-Problem`.BOJ

// 2096 acmicpc.net/problem/2096

fun main() {
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()

    val arr = Array(N) {
        br.readLine().split(" ").map {
            val num = it.toInt()
            num to num
        }.toMutableList()
    }

    fun getMinAndMax(rowIdx: Int, range: IntRange): Pair<Int, Int> {
        val row = arr[rowIdx]
        var min = Int.MAX_VALUE
        var max = 0
        range.forEach {
            min = minOf(min, row[it].first)
            max = maxOf(max, row[it].second)
        }
        return min to max
    }

    (1 until N).forEach { row ->
        (0..2).forEach { col ->
            val range = when (col) {
                0 -> 0..1
                1 -> 0..2
                else -> 1..2
            }
            val (min, max) = getMinAndMax(row - 1, range)
            arr[row][col] = arr[row][col].first + min to arr[row][col].second + max
        }
    }
    val (min, max) = getMinAndMax(N - 1, (0..2))
    val bw = System.out.bufferedWriter()
    bw.write("$max $min")
    bw.close()

}