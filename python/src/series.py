def slices(series, length):
    if length == 0:
        raise ValueError("slice length cannot be zero")
    
    if length < 0:
        raise ValueError("slice length cannot be negative")

    seriesLength = len(series)
    if seriesLength == 0:
        raise ValueError("series cannot be empty")

    if seriesLength < length:
        raise ValueError("slice length cannot be greater than series length")

    maxIdx = len(series) - length + 1

    result = []
    for i in range(0, maxIdx):
        result.append(series[i:i+length])

    return result
