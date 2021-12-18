package BOJ

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.BufferedWriter
import java.io.OutputStreamWriter

class IO1620 {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val bw = BufferedWriter(OutputStreamWriter(System.out))

    fun flush() = bw.flush()
    fun write(message: String) = bw.write(message)
    fun close() = bw.close()
    fun getInt(): Int = br.readLine().toInt()
    fun getPockemon(): String = br.readLine()
    fun getString(): String = br.readLine()
}

fun main() {
    val nameMap = hashMapOf<String, String>()
    val idMap = hashMapOf<String, String>()

    val io = IO1620()
    val pockemonNum = io.getInt()
    (1..pockemonNum).forEach { idx ->
        val sIdx = idx.toString()
        val name = io.getPockemon()
        nameMap[name] = sIdx
        idMap[sIdx] = name
    }
    repeat(io.getInt()) {
        val query = io.getString()
        if (nameMap.containsKey(query)) {
            io.write(nameMap[query]!! + "\n")
        } else if (idMap.containsKey(query)) {
            io.write(idMap[query]!! + "\n")
        }
    }
    io.flush()
    io.close()

}