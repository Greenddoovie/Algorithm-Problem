class IO1744 {
    private val br = System.`in`.bufferedReader()
    private val bw = System.out.bufferedWriter()
    fun getNum() = br.readLine().toInt()
    fun close() = bw.close()
    fun write(message: String) = bw.write(message)
}

fun main() {
    var answer = 0
    val io = IO1744()
    val N = io.getNum()

    val arr = Array(N) { io.getNum() }.apply {
        sortDescending()
    }
    val used = Array(N) { false }
    var idx = 0
    if (N == 1) {
        answer += arr[0]
        io.write(answer.toString())
        io.close()
        return
    }

    while (arr[idx] > 1) {
        if (arr[idx] > 1 && arr[idx+1] > 1) {
            answer += arr[idx] * arr[idx + 1]
            used[idx] = true
            used[idx + 1] = true
            idx += 2
            if (idx  > arr.lastIndex - 1) break
        } else {
            break
        }

    }

    idx = arr.lastIndex

    while (arr[idx] < 1) {
        if (arr[idx] < 1 && arr[idx - 1] < 1) {
            answer += arr[idx] * arr[idx - 1]
            used[idx] = true
            used[idx - 1] = true
            idx -= 2
            if (idx < 1) break
        } else {
            break
        }
    }

    used.forEachIndexed { idx , it ->
        if (!it) {
            answer += arr[idx]
        }
    }

    io.write(answer.toString())
    io.close()
}