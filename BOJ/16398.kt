class IO16398 {
    private val br = System.`in`.bufferedReader()
    private val bw = System.out.bufferedWriter()

    fun close() = bw.close()
    fun write(message: String) = bw.write(message)
    fun getN() = br.readLine().toInt()
    fun getFlowRow() = br.readLine().split(" ").map { it.toInt() }
}

fun main() {
    var answer:Long = 0
    var edges = 0
    val io = IO16398()
    data class Flow(val planet1: Int, val planet2: Int, val cost: Int)

    val N = io.getN()
    val connected = Array(N) { it }
    val flows = mutableListOf<Flow>()

    repeat(N) { from ->
        val row = io.getFlowRow()
        for (to in from+1 until N) {
            flows.add(Flow(from, to, row[to]))
        }
    }

    fun checkAllConnection(): Boolean {
        return edges == N -1
    }

    fun findParent(p1: Int): Int {
        if (connected[p1] != p1) connected[p1] = findParent(connected[p1])
        return connected[p1]
    }

    fun findUnion(p1: Int, p2: Int): Boolean {
        var parent1 = findParent(p1)
        var parent2 = findParent(p2)
        return if (parent1 == parent2) {
            true
        } else {
            connected[parent1] = parent2
            false
        }
    }

    flows.sortBy { it.cost }

    run loop@ {
        flows.forEach { flow ->
            if (checkAllConnection()) {
                return@loop
            }
            val (p1, p2, cost) = flow
            val result = findUnion(p1, p2)
            if (result) {
                return@forEach
            } else {
                answer += cost
                edges++
            }
        }
    }
    io.write(answer.toString())
    io.close()
}