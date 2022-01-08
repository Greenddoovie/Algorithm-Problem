class IO1931 {
    private val br = System.`in`.bufferedReader()
    private val bw = System.out.bufferedWriter()

    fun close() = bw.close()
    fun flush() = bw.flush()
    fun write(message: String) = bw.write(message)
    fun getN() = br.readLine().toInt()
    fun getStartAndEndOfConference() = br.readLine().split(" ").map { it.toInt() }
}

fun main() {
    val io = IO1931()
    var answer = 1

    var arr = mutableListOf<Pair<Int, Int>>()
    repeat(io.getN()) {
        val (start, end) = io.getStartAndEndOfConference()
        arr.add(start to end)
    }

    arr.sortWith(compareBy({it.second}, {it.first}))
    // second first 순으로 해줘야함.

    var end = arr.first().second
    var idx = 1

    while (idx < arr.size) {
        if (arr[idx].first >= end) {
            end = arr[idx].second
            answer++
        }
        idx++
    }

    io.write(answer.toString())
    io.close()

}