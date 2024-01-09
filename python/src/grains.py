SQUARE_COUNT = 64

def square(number):
    if number not in range(1, SQUARE_COUNT+1):
        raise ValueError(f"square must be between 1 and {SQUARE_COUNT}")
    return 2 ** (number - 1)


def total():
    return 2 ** SQUARE_COUNT - 1
