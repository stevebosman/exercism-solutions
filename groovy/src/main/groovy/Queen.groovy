class Queen {
    public int row
    public int column

    Queen(int row, int column) {
        if (row<0 || row>7) throw new IllegalArgumentException()
        if (column<0 || column>7) throw new IllegalArgumentException()
        this.row = row
        this.column = column
    }
}
