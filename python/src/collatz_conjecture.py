def steps(number):
    if number < 1:
        raise ValueError("Only positive integers are allowed")
    steps = 0
    current = number
    while current != 1:
        if current % 2 == 0:
            current /= 2
        else:
            current = 3 * current + 1
        steps +=1
    return steps
