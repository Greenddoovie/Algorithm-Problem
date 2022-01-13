class IO11657 {
    private val br = System.`in`.bufferedReader()
    private val bw = System.out.bufferedWriter()

    fun getNM() = br.readLine().split(" ").map { it.toInt() }
    fun getRow() = br.readLine().split(" ").map { it. toInt() }
    fun close() = bw.close()
    fun write(message: String) = bw.write(message)
}

fun main() {
    val io = IO11657()
    val (N, M) = io.getNM()
    val arr = Array(M) { io.getRow() }
    val distance = Array(N + 1) { Long.MAX_VALUE }
    var flag = false

    distance[1] = 0

    for (i in 1..N) {
        for (j in 0 until M) {
            val (curNode, nextNode, cost) = arr[j]

            if (distance[curNode] != Long.MAX_VALUE && distance[nextNode] > distance[curNode] + cost) {
                distance[nextNode] = distance[curNode] + cost
                if ( i == N ) {
                    flag= true
                }
            }
        }
    }

    if (flag) {
        io.write( (-1).toString())
        io.close()
    } else {
        for (idx in 2..N) {
            if (distance[idx] == Long.MAX_VALUE) {
                io.write("-1\n")
            } else {
                io.write("${distance[idx]}\n")
            }
        }
        io.close()
    }

}