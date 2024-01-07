#include <math.h>
#include "armstrong_numbers.h"
bool is_armstrong_number(int num) {
    int len = 1 + log10(num);

    int total = 0;
    int working = num;
    while (working > 0) {
        total += pow(working%10, len);
        working /= 10;
    }

    return num == total;
}
