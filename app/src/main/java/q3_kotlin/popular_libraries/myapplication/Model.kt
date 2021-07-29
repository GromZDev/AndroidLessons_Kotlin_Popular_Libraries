package q3_kotlin.popular_libraries.myapplication

class Model {
    private val counters = mutableListOf(0, 0, 0)

    private fun getCurrent(index: Int): Int {
        return counters[index]
    }

    fun next(index: Int): Int {
        counters[index]++
        return getCurrent(index)
    }

    fun set(index: Int, value: Int) {
        counters[index] = value
    }
}