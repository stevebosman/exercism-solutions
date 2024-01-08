package pangram

import "strings"

func IsPangram(input string) bool {
	input = strings.ToLower(input)

	for char := 'a'; char <= 'z'; char++ {
		if !strings.ContainsRune(input, char) {
			return false
		}
	}
	return true
}
