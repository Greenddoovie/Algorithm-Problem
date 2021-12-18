package BOJ

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*


class IO11286 {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val bw = BufferedWriter(OutputStreamWriter(System.out))

    fun getInt(): Int = br.readLine().toInt()
    fun flush() = bw.flush()
    fun close() = bw.close()
    fun write(message: String) = bw.write(message)
}

fun main() {
    val io = IO11286()
    val count = io.getInt()

    val abs = { num: Int -> if (num <0) -num else num }

    val pq = PriorityQueue<Int>{ a,b ->
        when {
            abs(a) > abs(b) -> 1
            abs(a) < abs(b) -> -1
            else -> when {
                a > b -> 1
                a < b -> -1
                else -> 0
            }
        }
    }
    repeat(count) {
        val num = io.getInt()
        if (num == 0) {
            io.write( if (pq.isEmpty()) "0\n" else "${pq.poll()}\n")
        } else {
            pq.offer(num)
        }
    }

    io.apply {
        flush()
        close()
    }
}