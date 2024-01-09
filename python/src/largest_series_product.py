def max(num1, num2):
    if num1 > num2:
        return num1
    return num2


def largest_product(series, size):
    if size <= 0:
        raise ValueError("span must not be negative")
    
    maxIdx = len(series) - size + 1
    if maxIdx <= 0:
        raise ValueError("span must be smaller than string length")

    if not series.isnumeric():
        raise ValueError("digits input must only contain digits")

    maxProduct = 0
    for i in range(0, maxIdx):
        sequence = series[i:i+size]
        product = 1
        for j in sequence:
            product = product * int(j)        
        maxProduct = max(maxProduct, product)
        print(f"{sequence}: {product}")

    return maxProduct
