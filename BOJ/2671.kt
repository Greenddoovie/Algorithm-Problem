package BOJ

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class IO2671 {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val bw = BufferedWriter(OutputStreamWriter(System.out))

    fun close() = bw.close()
    fun flush() = bw.flush()
    fun write(message: String) = bw.write(message)
    fun getString(): String = br.readLine()
}

fun main() {
    val io = IO2671()
    val binary = io.getString()
    var flag = true
    binary.split("100").forEachIndexed { idx, split ->
        if (idx == 0) {
            if (split != "") {
                """(01)*""".toRegex().findAll(split).also { result ->
                    if (!checkEquality(result, split)) {
                        flag = false
                    }
                    if (!flag) return@forEachIndexed
                }
            }
        } else {
            val target = "100$split"
            """((10[0]+[1]+)|(01))*""".toRegex().findAll(target).also { result ->
                if (!checkEquality(result, target)) {
                    flag = false
                }
                if (!flag) return@forEachIndexed
            }
        }
    }
    io.write(if (flag) "SUBMARINE" else "NOISE")
    io.flush()
    io.close()
}

fun checkEquality(result: Sequence<MatchResult>, target: String): Boolean {
    var str = ""
    result.iterator().forEach {
        str += it.value
    }
    return str == target
}