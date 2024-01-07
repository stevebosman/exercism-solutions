#include "isogram.h"
#include <ctype.h>
#include <stdbool.h>
#include <string.h>

bool is_isogram(const char* word) {
    if (word == NULL) return false;
    int max = strlen(word);
    for(int i = 0; i < max - 1; i++) {
        char c = toupper(word[i]);
        if (isalpha(c)) {
            for(int j = i + 1; j < max; j++) {
                if (toupper(word[j]) == c) return false;
            }
        }
    }
    return true;
}
