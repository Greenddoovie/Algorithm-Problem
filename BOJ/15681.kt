package `Algorithm-Problem`.BOJ

fun main() {
    val br = System.`in`.bufferedReader()
    val (N, R, Q) = br.readLine().split(" ").map { it.toInt() }

    data class Node(val id: Int, val child: MutableList<Int> = mutableListOf() )
    val arr = Array(N + 1) { Node(it) }
    val countArr = Array(N + 1) { 0 }
    repeat(N - 1) {
        val (U, V) = br.readLine().split(" ").map { it.toInt() }
        arr[U].child.add(V)
        arr[V].child.add(U)
    }

    fun makeTreeAndCount(currentNode: Node, parent: Int) {
        var count = 1
        currentNode.child.forEach {
            arr[it].run {
                child.remove(parent)
                makeTreeAndCount(this, id)
            }
            count += countArr[it]
        }
        countArr[currentNode.id] = count
    }

    makeTreeAndCount(arr[R], R)

    val bw = System.out.bufferedWriter()
    repeat(Q) { bw.write("${countArr[br.readLine().toInt()]}\n") }
    bw.close()
}