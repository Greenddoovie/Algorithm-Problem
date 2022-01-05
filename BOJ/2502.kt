package `Algorithm-Problem`.BOJ

class IO2502 {
    private val br = System.`in`.bufferedReader()
    private val bw = System.out.bufferedWriter()

    fun getDayAndCount() = br.readLine().split(" ")
    fun flush() = bw.flush()
    fun close() = bw.close()
    fun write(message: String) = bw.write(message)
}

fun main() {
    val io = IO2502()
    val (day, count) = io.getDayAndCount().map { it.toInt() }

    var arr = getFibonacci(day)

    var a = arr[day-3]
    var b = arr[day-2]

    var B = count / b

    while ( B >= 1) {
        val rest = count - (B * b)
        if (rest / a > 0 && rest % a == 0) {
            io.write("${rest/a}\n")
            io.write("$B")
            break
         } else {
             B--
        }
    }
    io.flush()
    io.close()
}

fun getFibonacci(day: Int): Array<Int> {
    return Array(day) { -1 }.apply {
        set(0, 1)
        set(1, 1)

        for (idx in 2 until day) {
            set(idx, get(idx-1) + get(idx -2))
        }
    }
}

