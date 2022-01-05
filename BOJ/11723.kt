class IO11723 {
    private val br = System.`in`.bufferedReader()
    private val bw = System.out.bufferedWriter()

    fun getM() = br.readLine().toInt()
    fun getOperation() = br.readLine().split(" ")
    fun flush() = bw.flush()
    fun close() = bw.close()
    fun write(message: String) = bw.write(message)
}

fun main() {
    val io = IO11723()
    val m = io.getM()

    var bit: Long = 0b0 shl 19

    repeat(m) {
        val op = io.getOperation()
        val num = if (op.size == 2) op[1].toInt() else 0
        when (op[0]) {
            "add" -> {
                val comparison = 0b1 shl (num - 1)
                bit = bit or comparison.toLong()
            }
            "remove" -> {
                val str = bit.toString(2)
                if (str.length >= num && str[str.lastIndex - num + 1] == '1') {
                    val comparison = 0b1 shl (num - 1)
                    bit = bit xor comparison.toLong()
                }
            }
            "check" -> {
                val str = bit.toString(2)
                if (str.length >= num && str[str.lastIndex - num + 1] == '1') {
                    io.write("1\n")
                } else {
                    io.write("0\n")
                }
            }
            "toggle" -> {
                val comparison = 0b1 shl (num -1)
                bit = (bit xor comparison.toLong())
            }
            "all" -> {
                bit = 0b1
                repeat (19) {
                    bit = (bit shl 1) or 0b1
                }
            }
            "empty" -> {
                bit = 0b0 shl 19
            }
        }
    }

    io.flush()
    io.close()

}


