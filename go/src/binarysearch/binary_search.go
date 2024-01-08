package binarysearch

func SearchInts(list []int, key int) int {
	l := len(list)
	if l == 0 {
		return -1
	}
	i := l / 2
	v := list[i]
	if v == key {
		return i
	} else if v > key {
		result := SearchInts(list[:i], key)
		if result < 0 {
			return -1
		}
		return result
	} else {
		result := SearchInts(list[i+1:], key)
		if result < 0 {
			return -1
		}
		return i + result + 1
	}
}
