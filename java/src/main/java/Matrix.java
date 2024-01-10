
class Matrix {
    final int[][] rows;
    final int[][] cols;

    Matrix(String matrixAsString) {
        final String[] rowsAsString = matrixAsString.split("\n");

        final int rowCount = rowsAsString.length;
        final int colCount = rowsAsString[0].split(" ").length;
        
        this.rows = new int[rowCount][colCount];
        this.cols = new int[colCount][rowCount];
        
        for (int i = 0; i < rowCount; i++) {
            final String[] rowCellsAsString = rowsAsString[i].split(" ");
            for (int j = 0; j < colCount; j++) {
                final int val = Integer.valueOf(rowCellsAsString[j]);
                this.rows[i][j] = val;
                this.cols[j][i] = val;
            }
        }
    }

    int[] getRow(int rowNumber) {
        return rows[rowNumber - 1].clone();
    }

    int[] getColumn(int columnNumber) {
        return cols[columnNumber - 1].clone();
    }
}
