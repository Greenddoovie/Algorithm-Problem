package `Algorithm-Problem`.BOJ

fun main() {
    class Solution {
        fun solution(stones: IntArray, k: Int): Int {
            var answer = 0
            var s = 1
            var e = stones.max()
            while (s <= e) {
                val mid = (s + e) / 2
                if (checkCrossPossibility(mid, stones, k)) {
                    s = mid + 1
                    if (answer < mid) {
                        answer = mid
                    }
                } else {
                    e = mid - 1
                }
            }
            return answer
        }

        fun checkCrossPossibility(mid: Int, stones: IntArray, k: Int): Boolean {
            var diff = 0
            stones.forEach {
                if (it >= mid) {
                    if (diff >= k) return false
                    diff = 0
                } else diff++
            }
            return diff < k
        }

        fun IntArray.max(): Int {
            var max = 0
            this.forEach { if (max < it) max = it }
            return max
        }
    }
    val solution = Solution()
    var stones = intArrayOf(2,4,5,3,2,1,4,2,5,1)
    var k = 3
    var result = solution.solution(stones, k)
    println(result)

    stones = intArrayOf(100)
    k = 1
    result = solution.solution(stones, k)
    println(result)

    stones = intArrayOf(1,2,3)
    k = 3
    result = solution.solution(stones, k)
    println(result)
}

/**
 * var answer = -1
var flag = false
var tmpStones = stones.toMutableList()
while (!flag) {
var diff = 0
answer++
tmpStones.maxOrNull()?.let {
if (it == 0) return answer
} ?: return answer
tmpStones = tmpStones.map {
if (it > 0) {
if (diff >= k) {
flag = true
}
diff = 0
it -1
} else {
diff += 1
0
}
}.toMutableList()
}
return answer
 */