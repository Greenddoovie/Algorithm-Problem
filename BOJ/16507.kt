fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (R, C, Q) = br.readLine().split(" ").map { it.toInt() }

    val cumGraph = Array(R) { Array(C) { -1L } }

    (0 until R).forEach { row ->
        br.readLine().split(" ").map { it.toLong() }.also { rows ->
            rows.forEachIndexed { col, value ->
                cumGraph[row][col] = if (col == 0) value else value + cumGraph[row][col - 1]
            }
        }
    }

    repeat(Q) {
        val (r1, c1, r2, c2) = br.readLine().split(" ").map { it.toInt() - 1 }
        val count = (r2 - r1 + 1) * (c2 - c1 + 1)
        var total = 0L
        (r1..r2).forEach { row ->
            total += if (c1 == 0) {
                cumGraph[row][c2]
            } else {
                cumGraph[row][c2] - cumGraph[row][c1 - 1]
            }
        }
        bw.write("${total / count}\n")
    }
    bw.close()

}