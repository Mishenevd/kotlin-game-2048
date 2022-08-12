package games.game2048

/*
 * This function moves all the non-null elements to the beginning of the list
 * (by removing nulls) and merges equal elements.
 * The parameter 'merge' specifies the way how to merge equal elements:
 * it returns a new element that should be present in the resulting list
 * instead of two merged elements.
 *
 * If the function 'merge("a")' returns "aa",
 * then the function 'moveAndMergeEqual' transforms the input in the following way:
 *   a, a, b -> aa, b
 *   a, null -> a
 *   b, null, a, a -> b, aa
 *   a, a, null, a -> aa, a
 *   a, null, a, a -> aa, a
 *
 * You can find more examples in 'TestGame2048Helper'.
*/
fun <T : Any> List<T?>.moveAndMergeEqual(merge: (T) -> T): List<T> {
    val nonNullList = this.filterNotNull().toMutableList()

    with(nonNullList) {
        var idx = 1
        while (idx < size) {
            val first = nonNullList[idx]
            val second = nonNullList[idx-1]
            if(first == second) {
                val mergedElement = merge(first)
                repeat(2) {
                    removeAt(idx-1)
                }
                add(idx-1, mergedElement)
            }
            idx++
        }
    }
    return nonNullList
}

fun MutableList<Int>.swapElements(firstIdx:Int = random(), secondIdx: Int = random()) {
    if ((size > firstIdx) and (size > secondIdx)) {
        run {val temp = this[firstIdx]; this[firstIdx] = this[secondIdx]; this[secondIdx] = temp }
    }
}

fun MutableList<Int?>.swapNullableElements(firstIdx:Int, secondIdx: Int): Boolean {
    if ((size > firstIdx) and (size > secondIdx) and (firstIdx >= 0) and (secondIdx >= 0)) {
        run {val temp = this[firstIdx]; this[firstIdx] = this[secondIdx]; this[secondIdx] = temp }
        return true
    }
    return false
}