# Heron's formula
def heron(number, estimate):
    return (number / estimate + estimate) / 2

# absolute value
def abs(number):
    if number < 0:
        return -number
    return number

# calculate square root using Heron's formula
def square_root(number):
    s0 = number
    s1 = number / 3
    
    while abs(s1 - s0) > 0.00001:
        s2 = heron (number, s1)
        s0 = s1
        s1 = s2
    return s1
