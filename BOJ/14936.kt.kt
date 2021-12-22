import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class IO14936 {
    private val bw = BufferedWriter(OutputStreamWriter(System.out))
    private val br = BufferedReader(InputStreamReader(System.`in`))

    fun getNM() = br.readLine().split(" ").map { it.toInt() }
    fun write(message: String) = bw.write(message)
    fun flush() = bw.flush()
    fun close() = bw.close()
}

fun main() {
    var ans = 1

    val io = IO14936()
    val (N,M) = io.getNM()

    val all = N
    val odd = if(N%2 == 0)  N/2 else N/2+1
    val even = N/2
    val k3 = N/3 + if (N%3 >= 1) 1 else 0

    if (M >= all) { ans ++ }
    if (M >= odd && N > 1) { ans++ }
    if (M >= even && N > 1) { ans++ }
    if (M >= k3 && N > 2) { ans++ }
    if (M >= k3 + odd && N > 2) { ans++ }
    if (M >= k3 + even && N > 2) { ans++ }
    if (M >= k3 + all && N > 2) { ans++ }

    io.write(ans.toString())
    io.flush()
    io.close()
}
