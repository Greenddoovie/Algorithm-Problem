package `Algorithm-Problem`.BOJ

// 수들의 합4

fun main() {
    val br = System.`in`.bufferedReader()

    val (N, K) = br.readLine().split(" ").map { it.toInt() }
    val pSum = Array(N+1) { 0 } // 누적합 (prefix Sum)
    val map = hashMapOf<Int, Long>() // pSum 을 순차적으로 돌며 누적합 값의 개수 증가 시킴

    br.readLine().split(" ").forEachIndexed { idx, it ->
        pSum[idx + 1] = pSum[idx] + it.toInt()
    }

    var ans: Long = 0

    (1..N).forEach {
        if (pSum[it] == K) ans++

        ans += map.getOrDefault(pSum[it] - K, 0)

        if (map.containsKey(pSum[it])) {
            map[pSum[it]] = map[pSum[it]]!! + 1
        } else {
            map[pSum[it]] = 1
        }
    }

    val bw = System.out.bufferedWriter()
    bw.write(ans.toString())
    bw.close()
}