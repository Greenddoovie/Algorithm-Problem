import java.util.*

class IO1753 {
    private val br = System.`in`.bufferedReader()
    private val bw = System.out.bufferedWriter()

    fun close() = bw.close()
    fun flush() = bw.flush()
    fun write(message: String) = bw.write(message)
    fun getVE() = br.readLine().split(" ").map { it.toInt() }
    fun getStartingPoint() = br.readLine().toInt()
    fun getEInfo() = br.readLine().split(" ").map { it.toInt() }
}

fun main() {
    val io = IO1753()
    val (V, E) = io.getVE()
    val start = io.getStartingPoint() - 1

    data class Node(val w: Int, val v: Int)

    val graph = Array(V) { mutableListOf<Node>() }
    val disArr = Array(V) { Int.MAX_VALUE }

    repeat(E) {
        val (u, v, w) = io.getEInfo()
        graph[u-1].add(Node(w, v-1))
    }

    val q = PriorityQueue<Node>(compareBy {
        it.w
    })

    disArr[start] = 0
    q.add(Node(disArr[start], start))

    while (q.isNotEmpty()) {
        val (curW, curV) = q.poll()
        val adjList = graph[curV]

        if (disArr[curV] < curW) continue

        for (idx in adjList.indices) {
            val (w, v) = adjList[idx]
            val newW = (curW + w)

            if (newW < disArr[v]) {
                disArr[v] = newW
                q.add(Node(newW, v))
            }
        }
    }

    disArr.forEachIndexed { idx, it ->
        if (it == Int.MAX_VALUE) {
            if (idx == disArr.lastIndex) {
                io.write("INF")
            } else {
                io.write("INF\n")
            }
        } else {
            io.write("$it\n")
        }
    }

    io.flush()
    io.close()
}