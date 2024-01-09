def saddle_points(matrix):
    if len(matrix) == 0:
        return {}
    dimensions = size(matrix)
    saddle_point_set = (tallest_per_row(matrix, dimensions)
                        .intersection(shortest_per_column(matrix, dimensions)))

    row_maxima = list(map(max, matrix))
    print(f"{matrix}")
    print(f"{zip(*matrix)}")
    print(f"{list(zip(*matrix))}")
    col_minima = list(map(min, list(zip(*matrix))))

    return [{'column': p[1] + 1, 'row': p[0] + 1} for p in saddle_point_set]

def size(matrix):
    return (len(matrix), len(matrix[0]))

def max_value(matrix):
    maxv = matrix[0][0]
    for row in matrix:
        maxv = max(maxv, max(row))
    return maxv

def tallest_per_row(matrix, dimensions):
    maxima = set()
    for r in range(0, dimensions[0]):
        row = matrix[r]
        if len(row) != dimensions[1]:
            raise ValueError("irregular matrix")
        row_max = max(row)
        row_maxima = set()
        for c in range(0, dimensions[1]):
            if row[c] == row_max:
                maxima.add( (r,c) )

    return maxima

def shortest_per_column(matrix, dimensions):
    minima = set()
    start_max = max_value(matrix) + 1
    for c in range(0, dimensions[1]):
        col_min = start_max
        col_minima = set()
        for r in range(0, dimensions[0]):
            v = matrix[r][c]
            if v <= col_min:
                if v < col_min:
                    col_minima = set()
                    col_min = v
                col_minima.add( (r,c) )
        minima = minima.union(col_minima)

    return minima
