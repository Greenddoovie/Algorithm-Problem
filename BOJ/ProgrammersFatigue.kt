package `Algorithm-Problem`.BOJ

fun main() {
    class Solution {
        fun solution(k: Int, dungeons: Array<IntArray>): Int {
            var answer: Int = -1
            val visited = Array(dungeons.size) { false }
            fun dfs(count: Int, fatigue: Int) {
                if (count > answer) answer = count
                dungeons.forEachIndexed { idx, dungeon ->
                    if (!visited[idx]) {
                        if (fatigue >= dungeon.component1()) {
                            visited[idx] = true
                            dfs(count + 1, fatigue - dungeon.component2())
                            visited[idx] = false
                        }
                    }
                }
            }
            dfs(0, k)
            return answer
        }
    }

    val s = Solution()::solution
    var k = 80
    var dungeons = arrayOf(intArrayOf(80,20), intArrayOf(50,40), intArrayOf(30,10))
    println(s(k, dungeons))
}