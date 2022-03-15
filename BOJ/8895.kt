package `Algorithm-Problem`.BOJ

fun main() {
    val br = System.`in`.bufferedReader()
    val dp = Array (21) { Array(21) { Array(21) { 0L } } }
    dp[1][1][1] = 1
    for (i in 2..20) {
        for (j in 1..i) {
            for (k in 1..i) {
                dp[i][j][k] = dp[i-1][j-1][k] + dp[i-1][j][k-1] + (i - 2) * dp[i-1][j][k]
            }
        }
    }
    val bw = System.out.bufferedWriter()
    repeat(br.readLine().toInt()) {
        val (n,l,r) = br.readLine().split(" ").map { it.toInt() }
        bw.write(dp[n][l][r].toString())
        bw.write("\n")
    }
    bw.flush()
    bw.close()
}