package board

import board.Direction.*

class SquareBoardImpl(override val width: Int): SquareBoard {

    private val cells:List<List<Cell>>

    init {
        val widthRange = (1 .. width)
        cells = widthRange
            .map { rowIndex -> widthRange
                .map { columnIndex -> Cell(rowIndex, columnIndex) }
            }
    }

    override fun getCellOrNull(i: Int, j: Int): Cell? = cells
        .getOrNull(i-1)
        ?.getOrNull(j-1)

    override fun getCell(i: Int, j: Int): Cell = cells
            .getOrIllegalArgumentException(i)
            .getOrIllegalArgumentException(j)

    override fun getAllCells(): Collection<Cell> = cells.flatten()

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        val raw = cells.getOrIllegalArgumentException(i)
        return jRange.mapNotNull { raw.getOrNull(it-1) }
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        val column = getAllCells()
            .filter { cell -> cell.j == j }
        return iRange.mapNotNull { row -> column.find { cell -> cell.j == j && cell.i == row }  }
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        val (row, column) = when(direction) {
            UP -> i-1 to j
            DOWN -> i+1 to j
            LEFT -> i to j-1
            RIGHT -> i to j+1
        }
        return getCellOrNull(row, column)
    }

    private fun <T> List<T>.getOrIllegalArgumentException(i: Int): T =
        this.getOrNull(i-1)?: throw IllegalArgumentException("Board width is less than $i")
}