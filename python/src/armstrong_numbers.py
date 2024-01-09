def is_armstrong_number(number):
    string = str(number)
    exp = len(string)
    total = sum([int(ch) ** exp for ch in string])
    return number == total
