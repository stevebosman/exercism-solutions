#include "hamming.h"
#include <string.h>

int compute(const char* code1, const char* code2) {
    int diff = -1;
    int len1 = strlen(code1);
    int len2 = strlen(code2);
    if (len1 == len2) {
        diff = 0;
        for(int i = 0; i < len1; i++) {
            if (code1[i] != code2[i]) diff++;
        }
    }
    return diff;
}
