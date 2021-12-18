package BOJ

class GroupChecker1316 {
}


fun main() {
    val map = getAlphabetMap()
    val strList = listOf("aba" ,"abab", "abcabc", "a")
    var ans = 0

    strList.forEach { word ->
        var now = "1"
        var flag = false
        word.forEach {
            val str = it.toString()
            if (map[str] == false) {
                map[str] = true
                now = str
            } else if (map[str] == true && now != str) {
                flag = true
                return@forEach
            }
        }
        if (!flag) {
            ans++
        }
        resetAlphabetMap(map)
    }
    println(ans)
}


fun getAlphabetMap(): HashMap<String, Boolean> {
    return hashMapOf<String, Boolean>().also { map ->
        ('a'..'z').forEach { it->
            map[it.toString()] = false
        }
    }
}

fun resetAlphabetMap(map: HashMap<String, Boolean>) = map.keys.forEach { key -> map[key] = false }