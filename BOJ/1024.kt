import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class IO1024 {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val bw = BufferedWriter(OutputStreamWriter(System.out))

    fun getNumList(): List<Int> = br.readLine().split(" ").map { it.toInt() }
    fun flush() = bw.flush()
    fun close() = bw.close()
    fun write(message: String) = bw.write(message)
}

fun main() {
    val io = IO1024()
    val (N, L) = io.getNumList()

    val isOdd = { num: Int -> num%2 == 1 }

    val sum = { numList: MutableList<Int> -> if (numList.size ==0) 0 else numList.reduce { acc, int -> acc + int } }

    val getPlusMinus = { oddFlag: Boolean, num: Int, plusMinus: Int ->
        when (oddFlag) {
            true -> listOf (num-plusMinus, num+plusMinus)
            false -> listOf (num-plusMinus, num + plusMinus + 1)
        }
    }

    val ansList = mutableListOf<Int>()
    for (len in L..100) {
        var count = 0
        val oddFlag = isOdd(len)
        val mid = N / len
        val whileFlag = if (oddFlag && N % len == 0) {
            count = 1
            ansList.add(mid)
            true
        } else if (!oddFlag && N % len == len / 2) {
            count = 2
            ansList.addAll(listOf(mid, mid + 1))
            true
        } else {
            false
        }
        while (count < len && whileFlag) {
            val plusMinus = if (oddFlag) (count + 1) / 2 else count / 2
            if (mid - plusMinus < 0) {
                ansList.clear()
                break
            }
            ansList.addAll( getPlusMinus(oddFlag, mid, plusMinus))
            count += 2
        }
        if (sum(ansList) == N ) {
            break
        } else {
            ansList.clear()
        }
    }
    ansList.sort()
    val message = when {
        ansList.size == 0 -> "-1"
        ansList.size > 100 -> "-1"
        else -> {
            ansList.map { it.toString() }.reduce { acc, str -> "$acc $str" }
        }
    }
    io.write(message.trim())
    io.flush()
    io.close()
}
