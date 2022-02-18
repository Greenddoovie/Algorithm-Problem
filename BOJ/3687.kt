package `Algorithm-Problem`.BOJ

fun main() {
    val br = System.`in`.bufferedReader()
    val map = hashMapOf(
        2 to 1, 3 to 7, 4 to 4, 5 to 2, 6 to 0, 7 to 8
    )
    val dp = Array(101) { Long.MAX_VALUE }.apply {
        set(2, 1)
        set(3, 7)
        set(4, 4)
        set(5, 2)
        set(6, 6)
        set(7, 8)
    }

    var idx = 8
    while (idx <= 100) {
        (2..7).forEach {
            if (dp[idx - it] == Long.MAX_VALUE) return@forEach
            dp[idx] = minOf(dp[idx], dp[idx - it] * 10 + map[it]!!)
        }
        idx++
    }

    repeat(br.readLine().toInt()) {
        val n = br.readLine().toInt()
        val a = n / 2
        val b = n % 2
        val max = StringBuilder().apply {
            if (b == 1) append("7") else append("1")
            repeat(a - 1) {
                append("1")
            }
        }.toString()
        val min = dp[n].toString()
        println("$min $max")
    }
}