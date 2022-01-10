class IO11047 {
    private val br = System.`in`.bufferedReader()
    private val bw = System.out.bufferedWriter()

    fun getNK() = br.readLine().split(" ").map { it.toInt() }
    fun getValue() = br.readLine().toInt()
    fun close() = bw.close()
    fun write(message: String) = bw.write(message)
}

fun main() {
    var answer = 0
    var curMoney = 0
    val io = IO11047()

    val (N, K) = io.getNK()

    val coins = Array(N) { 0 }

    repeat(N) {
        coins[it] = io.getValue()
    }

    coins.sort()

    for (idx in coins.lastIndex downTo 0) {
        val div = coins[idx]
        val now = K - curMoney
        val q = now / div
        val r = now % div
        if (q > 0) {
            curMoney += div * q
            answer += q
        } else {
            continue
        }
    }
    io.write(answer.toString())
    io.close()
}