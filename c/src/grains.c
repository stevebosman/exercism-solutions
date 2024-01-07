#include "grains.h"
#include <math.h>
#include <stdint.h>

uint64_t square(uint8_t index) {
    return (index > 0 && index < 65) ? pow(2.0, index - 1) : 0;
}

uint64_t total() {
    uint64_t two63 = pow(2.0, 63);
    return two63 - 1ull + two63;
}
