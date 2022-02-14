package `Algorithm-Problem`.BOJ

// 3584 가장 가까운 조상 acmicpc.net/problem/3584

fun main() {
    val br = System.`in`.bufferedReader()
    val T = br.readLine().toInt()
    val bw = System.out.bufferedWriter()
    repeat(T) {
        val N = br.readLine().toInt()
        val biGraph = Array(N + 1) { mutableListOf<Int>() } // idx: parent, list element: child

        repeat(N-1) {
            val (a, b) = br.readLine().split(" ").map{ it.toInt() }
            biGraph[a].add(b)
        }
        val (targetA, targetB) = br.readLine().split(" ").map { it.toInt() }

        val parent = Array(N+1) { 0 }
        biGraph.forEachIndexed { idx, it ->
            it.forEach { ele ->
                parent[ele] = idx
            }
        }

        val root = parent.lastIndexOf(0) // 0을 가지는 값은 index 0과 root 뿐
        val depth = Array(N+1) { 0 }
        fun dfs(root: Int, depthValue: Int) {
            depth[root] = depthValue
            biGraph[root].forEach {
                dfs(it, depthValue + 1)
            }
        }
        dfs(root, 0)

        fun lca(a: Int, b: Int): Int {
            var ca = a
            var cb = b

            while(depth[ca] != depth[cb]) {
                if (depth[ca] > depth[cb]) ca = parent[ca] else cb = parent[cb]
            }

            while (ca != cb) {
                ca = parent[ca]
                cb = parent[cb]
            }
            return ca
        }

        val ancestor = lca(targetA, targetB)
        bw.write("$ancestor\n")
    }
    bw.close()

}

