class IO1520 {
    private val br = System.`in`.bufferedReader()
    private val bw = System.out.bufferedWriter()

    fun getHeightAndWidth() = br.readLine().split(" ").map { it.toInt() }
    fun getRow() = br.readLine().split(" ").map{ it.toInt() }
    fun flush() = bw.flush()
    fun close() = bw.close()
    fun write(message: String) = bw.write(message)
}

fun main() {
    val io = IO1520()
    val (height, width) = io.getHeightAndWidth()

    val dx = arrayOf(-1,1,0,0)
    val dy = arrayOf(0,0,-1,1)

    val graph = Array(height) { Array (width) { -1 } }
    val dp = Array(height) { Array (width) { -1 } }

    repeat(height) {
        io.getRow().forEachIndexed { idx, h ->
            graph[it][idx] = h
        }
    }

    fun dfs(y: Int, x: Int): Int {
        if ( y == height -1 && x == width -1) {
            return 1
        }
        if (dp[y][x] != -1) return dp[y][x]
        else dp[y][x] = 0
        dy.forEachIndexed { idx, cy ->
            val nx = dx[idx] + x
            val ny = cy + y
            if (nx in 0 until width && ny in 0 until height && graph[y][x] > graph[ny][nx]) {
                dp[y][x] += dfs(ny, nx)
            }
        }
        return dp[y][x]
    }


    io.write(dfs(0,0).toString())
    io.flush()
    io.close()
}