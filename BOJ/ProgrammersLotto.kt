package `Algorithm-Problem`.BOJ

fun main() {
    class Solution {
        fun solution(lottos: IntArray, win_nums: IntArray): IntArray {
            val cntSame = lottos.toSet().intersect(win_nums.toSet())
            val cnt0 = lottos.count { it == 0 }

            val map = mapOf(6 to 1, 5 to 2, 4 to 3, 3 to 4, 2 to 5, 1 to 6, 0 to 6)
            return mutableListOf<Int>().apply {
                add(map.getOrDefault(cntSame.size + cnt0, 0))
                add(map.getOrDefault(cntSame.size, 0))
            }.toIntArray()
        }
    }
    fun IntArray.print() {
        print("[")
        print(this.map { it.toString() }.reduce { acc, str -> "$acc, $str"})
        println("]")
    }
    val s = Solution()::solution
    var lottos = intArrayOf(44,1,0,0,31,25)
    var win_nums = intArrayOf(31,10,45,1,6,19)
    s(lottos, win_nums).print()

    lottos = intArrayOf(0,0,0,0,0,0)
    win_nums = intArrayOf(38, 19, 20, 40, 15, 25)
    s(lottos, win_nums).print()

    lottos = intArrayOf(45,4,35,20,3,9)
    win_nums = intArrayOf(20,9,3,45,4,35)
    s(lottos, win_nums).print()
}