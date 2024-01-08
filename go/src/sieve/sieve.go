package sieve

func NewSieve(limit int) map[int]bool {
	sieve := make(map[int]bool)
	for i := 2; i <= limit; i++ {
		// composite is false
		sieve[i] = true
	}
	return sieve
}

func Sieve(limit int) []int {
	sieve := NewSieve(limit)

	var result []int
	for i := 2; i <= limit; i++ {
		if sieve[i] {
			result = append(result, i)
			for j := i * 2; j <= limit; j += i {
				sieve[j] = false
			}
		}
	}

	return result
}
