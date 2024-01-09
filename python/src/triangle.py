def triangle(sides):
    result = True
    if len(sides) != 3 or sides[0] <= 0 or sides[1] <= 0 or sides[2] <= 0:
        result = False
    else:
        sides.sort()
        result = sides[0] + sides[1] > sides[2]

    return result

def equilateral(sides):
    return triangle(sides) and len(set(sides)) == 1


def isosceles(sides):
    return triangle(sides) and len(set(sides)) <= 2


def scalene(sides):
    return triangle(sides) and len(set(sides)) == 3
