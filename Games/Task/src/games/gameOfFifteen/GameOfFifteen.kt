package games.gameOfFifteen

import board.Direction
import board.Direction.*
import games.game.Game
import games.game2048.swapNullableElements

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */
fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game =
     object: Game{
         private lateinit var elements: MutableList<Int?>
         private var emptyCellIdx = 15

         override fun initialize() {
             elements = initializer.initialPermutation
                 .toMutableList()
             elements.add(null)
         }

         override fun canMove(): Boolean = true

         override fun hasWon(): Boolean = elements
                 .filterNotNull()
                 .zipWithNext().all { (first, second) -> first <= second }

         override fun processMove(direction: Direction) {
             val wasSwapped = when (direction) {
                 UP -> elements.swapNullableElements(emptyCellIdx, emptyCellIdx + 4)
                 DOWN -> elements.swapNullableElements(emptyCellIdx, emptyCellIdx - 4)
                 RIGHT -> {
                     if (isInnerRowSwap(RIGHT)) {
                         elements.swapNullableElements(emptyCellIdx, emptyCellIdx - 1)
                     } else false
                 }
                 LEFT -> {
                     if (isInnerRowSwap(LEFT)) {
                         elements.swapNullableElements(emptyCellIdx, emptyCellIdx + 1)
                     } else false
                 }
             }
             if (wasSwapped) {
                 emptyCellIdx = elements.indexOf(null)
             }
         }

         override fun get(i: Int, j: Int): Int? = elements.getOrNull(4 * (i-1) + (j-1))

         private fun isInnerRowSwap(direction: Direction): Boolean {
             return when (direction) {
                 RIGHT -> ((emptyCellIdx +1) % 4 != 1)
                 LEFT -> ((emptyCellIdx +1) % 4 != 0)
                 else -> true
             }
         }
     }