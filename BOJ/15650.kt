class IO15650 {
    private val br = System.`in`.bufferedReader()
    private val bw = System.out.bufferedWriter()

    fun getNM() = br.readLine().split(" ").map { it.toInt() }
    fun write(message: String) = bw.write(message)
    fun close() = bw.close()
    fun flush() = bw.flush()
}

fun main() {
    val io = IO15650()
    val (n, m) = io.getNM()
    val ansSet = mutableListOf<String>()
    val visited = Array(n) { false }

    var cur = ""
    fun dfs(now: String, depth: Int, startNum: Int) {
        if (depth == m) {
            ansSet.add(now)
            return
        }
        for (idx in startNum..n) {
            if (!visited[idx-1]) {
                visited[idx-1] = true
                cur += idx.toString()
                dfs(cur, depth + 1, idx)
                cur = cur.substring(0, cur.lastIndex)
                visited[idx-1] = false
            }
        }
    }

    dfs("", 0, 1)

    ansSet.forEach { str ->
        var result = ""
        for (idx in (0..str.lastIndex)) {
            result += "${str[idx]} "
        }
        io.write(result.trim() + "\n")
    }
    io.flush()
    io.close()
}