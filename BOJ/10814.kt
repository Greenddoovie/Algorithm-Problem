package BOJ

import java.io.BufferedReader
import java.io.InputStreamReader

class IO10814 {
    private val br = BufferedReader(InputStreamReader(System.`in`))

    fun getInt(): Int {
        return br.readLine().toInt()
    }

    fun getPersonInfo(id: Int): Person {
        val (age, name) = br.readLine().split(" ")
        return Person(id, age.toInt(), name)
    }
}

data class Person(val id: Int, val age: Int, val name: String)

fun main() {
    val map = Map()
    sortKey(map.hashMap.keys).forEach { key ->
        map.hashMap[key]!!.forEach { person ->
            println("${person.age} ${person.name}")
        }
    }
}

fun sortKey(key: Set<Int>): MutableList<Int> {
    val keyList = key.toMutableList()
    val lIdx = keyList.lastIndex
    (0 until lIdx).forEach { count ->
        for (i in 1..lIdx-count) {
            val prev = keyList[i-1]
            val now = keyList[i]
            if (prev > now) {
                keyList[i-1] = now
                keyList[i] = prev
            }
        }
    }
    return keyList
}

class Map {
    private val _hashMap: HashMap<Int, MutableList<Person>> = hashMapOf<Int, MutableList<Person>>().apply {
        (1..200).forEach {
            put(it, mutableListOf<Person>())
        }
    }
    val hashMap get() = _hashMap
    private val io = IO10814()

    init {
        fillPerson()
    }

    private fun fillPerson() {
        val peopleCount = io.getInt()
        (1..peopleCount).forEach {
            val person = io.getPersonInfo(it)
            _hashMap[person.age]!!.add(person)
        }
    }
}