package board

class GameBoardImpl<T>(override val width: Int) : GameBoard<T>, SquareBoard by SquareBoardImpl(width) {

    private val cellStorage: MutableMap<Cell, T?> = this
        .getAllCells()
        .associateWith { null }
        .toMutableMap()

    override fun get(cell: Cell): T? = cellStorage[cell]

    override fun all(predicate: (T?) -> Boolean): Boolean = cellStorage
        .values
        .all(predicate)

    override fun any(predicate: (T?) -> Boolean): Boolean = cellStorage
        .values
        .any(predicate)

    override fun find(predicate: (T?) -> Boolean): Cell? = cellStorage
        .entries
        .find { (_, value) -> predicate(value) }
        ?.key

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> = cellStorage
        .filterValues(predicate)
        .keys

    override fun set(cell: Cell, value: T?) {
        cellStorage[cell] = value
    }

}