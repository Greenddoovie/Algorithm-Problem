import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class IO3107 {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val bw = BufferedWriter(OutputStreamWriter(System.out))

    fun getIPv6(): String = br.readLine()
    fun flush() = bw.flush()
    fun close() = bw.close()
    fun write(message: String) = bw.write(message)
}

fun main() {
    val io = IO3107()
    val contractions = io.getIPv6().split(":")

    val ipv6 = mutableListOf<String>()
    var rule2 = false

    contractions.forEach { str ->
        if (str == "" && !rule2) {
            repeat(8 - contractions.count { it != "" }) { ipv6.add("0000") }
            rule2 = !rule2
        } else if ( str =="") {
            return@forEach
        } else {
            val origin = if (str.length < 4) {
                var added = ""
                repeat(4 - str.length) {
                    added += "0"
                }
                "$added$str"
            } else {
                str
            }
            ipv6.add(origin)
        }
    }
    io.write(ipv6.reduce { acc, s -> "$acc:$s" })
    io.flush()
    io.close()
}