package BOJ

data class Pos(val x: Int, val y: Int)



//    val n = 4
//    val intArr1 = intArrayOf(4, 3, 2, 1)
//    val intArr2 = intArrayOf(0, 0, 0, 0)
//    val intArr3 = intArrayOf(0, 0, 9, 0)
//    val intArr4 = intArrayOf(1, 2, 3, 4)
//    val graph: Array<IntArray> = arrayOf(intArr1, intArr2, intArr3, intArr4)
//    val babyShark = BabyShark(0, 0)
//    graph.forEachIndexed { idx, intArr ->
//        intArr.forEachIndexed { sIndx, ele ->
//            if (ele == 9) {
//                babyShark.x = sIndx
//                babyShark.y = idx
//            }
//        }
//    }
//    val dx = intArrayOf(-1,1,0,0) // left, right, up, down
//    val dy = intArrayOf(0,0,-1,1)
//    val nowPos = Pos(babyShark.x, babyShark.y)
//    graph[nowPos.y][nowPos.x] = 0
//    // 현재 위치에서 먹을 수 있으면서 가까운 애들을 찾는다 (1회차)
//    val q = mutableListOf<Pos>()
//    q.add(nowPos)
//    while (true) {
//        val possibleEat = HashSet<Pos>()
//        val newQ = HashSet<Pos>()
//        while(q.isNotEmpty()) {
//            val pos = q.removeAt(0)
//            for (idx in 0 until 4) {
//                val newPos = Pos(pos.x + dx[idx], pos.y + dy[idx])
//                if (babyShark.size > graph[newPos.y][newPos.x]) {
//                    possibleEat.add(newPos)
//                    newQ.add(newPos)
//                }
//            }
//        }
//        if (possibleEat.size == 0 ) {
//            // ToDo newQ -> Q
//        } else {
//            // 가까운 녀석들 중 가장 위에, 가장 왼쪽을 선택한다 -> sort
//        }
//    }