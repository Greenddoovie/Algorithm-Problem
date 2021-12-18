package BOJ

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.BufferedWriter
import java.io.OutputStreamWriter

class IO9184 {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val bw = BufferedWriter(OutputStreamWriter(System.out))

    fun flush() = bw.flush()
    fun close() = bw.close()
    fun write(message: String) = bw.write(message)

    fun getIntList() = br.readLine().split(" ").map { it.toInt() }
}

fun main() {
    val io = IO9184()
    val check = { numList: List<Int> -> (numList.count { it == -1 } == 3) }
    val w = W()
    while (true) {
        val numList = io.getIntList()
        when (check(numList)) {
            true -> {
                break
            }
            false -> {
                val message = "w(${numList[0]}, ${numList[1]}, ${numList[2]}) = "
                io.write(message + w.find(numList) + "\n")
            }
        }
    }
    io.flush()
    io.close()
}

class W {
    val map = hashMapOf<List<Int>, Long>()

    init {
        (0..20).forEach { a ->
            (0..20).forEach { b ->
                (0..20).forEach { c ->
                    val key = listOf(a,b,c)
                    map[key] = dp(key)
                }
            }
        }
    }

    private fun dp(key: List<Int>): Long {
        val newKey = convertKey(key)
        if (map.containsKey(newKey)) {
            return map[newKey]!!
        }
        val (a,b,c) = newKey
        return when {
            a <= 0 || b <= 0 || c <= 0 -> {
                1
            }
            a > 20 || b > 20 || c > 20 -> {
                dp(listOf(20,20,20))
            }
            b in (a + 1) until c -> {
                dp(listOf(a, b, c-1)) + dp(listOf(a, b-1, c-1)) - dp(listOf(a, b-1, c))
            }
            else -> {
                dp(listOf(a-1, b, c)) + dp(listOf(a-1, b-1, c)) + dp(listOf(a-1, b, c-1)) - dp (listOf(a-1, b-1, c-1))
            }
        }
    }

    private fun convertKey(key: List<Int>): List<Int> {
        val countUnder0 = key.count { it <= 0 }
        val countOver20 = key.count { it > 20 }
        return if (countUnder0 > 0) {
            listOf(0,0,0)
        }else if (countOver20 > 0 ) {
            listOf(20,20,20)
        } else {
            key.map {
                if (it < 0) 0
                else it
            }
        }
    }

    fun find(key: List<Int>): Long {
        return map[convertKey(key)]!!
    }
}