import kotlin.math.max

class IO2208 {
    private val br = System.`in`.bufferedReader()
    private val bw = System.out.bufferedWriter()

    fun getNM() = br.readLine().split(" ").map { it.toInt() }
    fun getJewel() = br.readLine().toInt()
    fun close() = bw.close()
    fun write(message: String) = bw.write(message)
}

fun main() {
    var answer = 0
    val io = IO2208()
    val (N, M) = io.getNM()

    val jewels = Array(N + 1) { 0 }
    var sum = 0

    data class Node(val sum: Int, val min: Int)

    val arr = Array(N + 1) { Node(0, 0) }

    var min = Int.MAX_VALUE
    repeat(N) {
        val idx = it + 1
        jewels[idx] = io.getJewel()
        sum += jewels[idx]

        if (sum < min) {
            min = sum
        }

        arr[idx] = Node(sum, min)
        if (idx >= M) {
            answer = max (answer, max(arr[idx].sum, arr[idx].sum - arr[idx-M].min))
        }
    }
    for (idx in M .. N) {
        answer = max (answer, max(arr[idx].sum, arr[idx].sum - arr[idx-M].min))
    }
    arr.forEach {
        println(it)
    }
    io.write(answer.toString())
    io.close()
}

