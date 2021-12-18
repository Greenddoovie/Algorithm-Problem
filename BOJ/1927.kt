package BOJ

import java.io.BufferedReader
import java.io.InputStreamReader

class IO1927 {
    private val br = BufferedReader(InputStreamReader(System.`in`))

    fun getInt(): Int {
        return br.readLine().toInt()
    }
}

class MinHeap {
    val arr = ArrayList<Int>()

    fun insert(element: Int) {
        arr.add(element)
        var nowIdx = arr.lastIndex
        siftUp(nowIdx)
    }

    private fun siftUp(idx: Int) {
        var tmpIdx = idx
        while (tmpIdx > 0 ) {
            val parentIdx = (tmpIdx -1) / 2
            if (arr[parentIdx] < arr[tmpIdx]) {
                break
            } else {
                val tmp = arr[parentIdx]
                arr[parentIdx] = arr[tmpIdx]
                arr[tmpIdx] = tmp
                tmpIdx = parentIdx
            }
        }
    }

    fun remove(): Int {
        if (arr.size == 0) {
            return 0
        }
        var nowIdx = 0
        val tmp = arr.last()
        arr[arr.lastIndex] = arr[0]
        arr[0] = tmp
        val ret = arr.removeAt(arr.lastIndex)
        siftDown(nowIdx)
        return ret
    }

    private fun siftDown(idx: Int) {
        var tmpIdx = idx
        while (tmpIdx < arr.size) {
            val left = 2 * tmpIdx + 1
            val right = left + 1
            var candidate = arr[tmpIdx]
            var cIdx = tmpIdx
            if (left <= arr.lastIndex && arr[left] < arr[tmpIdx]) {
                cIdx = left
                candidate = arr[left]
            }
            if (right <= arr.lastIndex && arr[right] < candidate) {
                cIdx = right
                candidate = arr[right]
            }
            if (tmpIdx == cIdx) {
                break
            } else {
                arr[cIdx] = arr[tmpIdx]
                arr[tmpIdx] = candidate
                tmpIdx = cIdx
            }
        }
    }
}

fun main() {
    val io = IO1927()
    val heap = MinHeap()
    val numList = mutableListOf<Int>()
    repeat(io.getInt()) {
        numList.add(io.getInt())
    }
    numList.forEach { num ->
        if (num == 0) {
            println(heap.remove())
        } else {
            heap.insert(num)
        }
    }
}