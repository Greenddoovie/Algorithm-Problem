class IO6198 {
    private val br = System.`in`.bufferedReader()
    private val bw = System.out.bufferedWriter()

    fun getBuildingCount() = br.readLine().toInt()
    fun getBuildingHeight() = br.readLine().toInt()
    fun flush() = bw.flush()
    fun close() = bw.close()
    fun write(message: String) = bw.write(message)
}

fun main() {
    val io = IO6198()
    val buildingCount = io.getBuildingCount()
    val arr = Array(buildingCount) { -1 }
    repeat(buildingCount) {
        arr[it] = io.getBuildingHeight()
    }

    val countArr = Array(buildingCount) { 0 }
    val indexArr = Array(buildingCount) { -1 }
    var answer: Long = 0
    for (idx in buildingCount-1 downTo 0) {
        if (idx == buildingCount - 1) {
            indexArr[idx] = buildingCount
            continue
        }
        var nextIdx = idx + 1
        if (arr[idx] > arr[nextIdx]) {
            // 같거나 높은 빌딩을 만나기 전까지 가지고 있던 count에 자기 자신을 더한 값
            countArr[idx] = countArr[nextIdx] + 1

            // 같거나 높은 빌딩에 대한 처리 로직
            while (indexArr[nextIdx] < buildingCount) { // indexArr[nextIdx] == buildCont이면 nextIdx에는 끝까지 확인하여 더해진 경우가 들어가 있음
                val prev = arr[nextIdx] // 빌딩 높이
                val next = arr[indexArr[nextIdx]] // 벽 빌딩 높이
                if (prev <= next) {
                    if (arr[idx] >= next) {
                        countArr[idx]++ // 포함되지 않은 자기 자신
                        countArr[idx] += countArr[indexArr[nextIdx]] // 자기가 포함하고 있던 빌딩 갯수
                        nextIdx = indexArr[nextIdx]
                    } else {
                        break
                    }
                } else {
                    // 호출될 일 없을 듯
                    break
                }
            }

            indexArr[idx] = indexArr[nextIdx]
        } else {
            indexArr[idx] = idx + 1
        }
        answer += countArr[idx]
    }
    io.write(answer.toString())
    io.flush()
    io.close()
}

