def leap_year(year):
    is_leap = year % 4 == 0
    is_century = year % 100 == 0
    is_4centuries = year % 400 == 0
    return is_4centuries or (is_leap and not is_century)
