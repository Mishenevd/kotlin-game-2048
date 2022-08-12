package games.gameOfFifteen

/*
 * This function should return the parity of the permutation.
 * true - the permutation is even
 * false - the permutation is odd
 * https://en.wikipedia.org/wiki/Parity_of_a_permutation

 * If the game of fifteen is started with the wrong parity, you can't get the correct result
 *   (numbers sorted in the right order, empty cell at last).
 * Thus the initial permutation should be correct.
 */
fun isEven(permutation: List<Int>): Boolean {

    val listLastIdx = permutation.size-1

    val invertedPairsCounter = (0 until  listLastIdx)
        .flatMap {
                i ->
            val possiblePairIdxRange = (i+1 .. listLastIdx)
            possiblePairIdxRange.map { j -> i to j } }
        .count { (first, second) -> permutation[first] > permutation[second] }

    return (invertedPairsCounter % 2) == 0
}