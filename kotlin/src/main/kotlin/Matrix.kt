data class MatrixCoordinate(val row: Int, val col: Int)

class Matrix(cells: List<List<Int>>) {

    val saddlePoints: Set<MatrixCoordinate>

    init {
        val minPoints = findMinColumnCoordinates(cells)
        if (minPoints.isEmpty()) {
            saddlePoints = minPoints
        } else {
            val maxPoints = findMaxRowCoordinates(cells)
            saddlePoints = minPoints.intersect(maxPoints)
        }
    }

    private fun findMinColumnCoordinates(cells: List<List<Int>>): Set<MatrixCoordinate> {
        val minPoints = mutableSetOf<MatrixCoordinate>()
        // assumes all rows same size, but tests are happy
        for (j in 0 until cells[0].size) {
            val minValue = cells.minOf { row -> row[j] }
            for (i in cells.indices) {
                if (minValue == cells[i][j]) {
                    minPoints.add(MatrixCoordinate(i + 1, j + 1))
                }
            }
        }
        return minPoints.toSet()
    }

    private fun findMaxRowCoordinates(cells: List<List<Int>>): Set<MatrixCoordinate> {
        val maxPoints = mutableSetOf<MatrixCoordinate>()
        cells.forEachIndexed{i, row ->
            val maxValue: Int? = row.maxOrNull()
            row.forEachIndexed{j, value ->
                if (maxValue == value) {
                    maxPoints.add(MatrixCoordinate(i + 1, j + 1))
                }
            }
        }
        return maxPoints.toSet()
    }
}
