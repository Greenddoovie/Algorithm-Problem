package `Algorithm-Problem`.BOJ

fun main() {
    val br = System.`in`.bufferedReader()
    val (N, V) = br.readLine().split(" ").map { it.toInt() }
    val problems = br.readLine().split(" ").map { it.toInt() }

    val list = mutableListOf<MaxMin>()
    var idx = 0
    list.add(MaxMin(0, problems[idx], 0, problems[idx++]))

    while (idx < N) {
        val prev = list.last()
        if (prev.diff >= V) break
        val interest = problems[idx]
        list.add(
            if (prev.max < interest) {
                prev.copy(maxIdx = idx, max = interest)
            } else if (interest < prev.min) {
                prev.copy(minIdx = idx, min = interest)
            } else {
                prev.copy()
            })
        idx++
    }
    if (list.last().diff < V) {
        println(N)
        return
    }
    fun abs(a: Int) = if (a > 0) a else -a
    val max = maxOf(list.last().maxIdx, list.last().minIdx)
    val min = minOf(list.last().maxIdx, list.last().minIdx)

    if (min % 2 == max % 2) {
        println(1 + (max) / 2 + (max) % 2)
    } else {
        val flag = min == list.last().maxIdx // true -> max
        val stan = if (flag) list.last().min else list.last().max
        (0..if(flag) list.last().minIdx else list.last().maxIdx).forEach { idx ->
            if (abs(problems[idx]-stan) >= V) {
                if (idx % 2 == max % 2) {
                    println(1 + max / 2 + max % 2)
                    return
                }
            }
        }
        val step1 = 1 + (min) / 2 + (min) % 2
        val step2 = (max - min) / 2 + (max - min) % 2
        println(step1 + step2)
    }
}

data class MaxMin(val minIdx: Int, val min: Int, val maxIdx: Int, val max: Int) {
    val diff: Int
        get() = max - min
}
