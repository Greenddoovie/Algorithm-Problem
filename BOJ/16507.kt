fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (R, C, Q) = br.readLine().split(" ").map { it.toInt() }

    val cumGraph = Array(R + 1) { Array(C + 1) { 0L } }
    (1..R).forEach { row ->
        br.readLine().split(" ").map { it.toLong() }.also { rows ->
            rows.forEachIndexed { col, value ->
                cumGraph[row][col + 1] = value + cumGraph[row][col]
            }
            (1..C).forEach { tmpCol ->
                cumGraph[row][tmpCol] += cumGraph[row-1][tmpCol]
            }
        }
    }

    repeat(Q) {
        val (r1, c1, r2, c2) = br.readLine().split(" ").map { it.toInt() }
        bw.write("${(cumGraph[r2][c2] - cumGraph[r2][c1 -1] - cumGraph[r1 -1][c2] + cumGraph[r1 -1][c1 - 1]) / ((r2 - r1 + 1) * (c2 - c1 + 1))}\n")
    }
    bw.close()

}