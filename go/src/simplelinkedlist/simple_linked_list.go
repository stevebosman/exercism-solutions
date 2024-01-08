package simplelinkedlist

import (
	"errors"
)

// Define the List and Element types here.
type Element struct {
	Value int
	next  *Element
}

type List struct {
	head *Element
	size int
}

// Create a new list
func New(elements []int) *List {
	var first *Element
	var previous *Element
	size := 0
	for _, element := range elements {
		element := &Element{
			Value: element,
		}
		if first == nil {
			first = element
		}
		if previous != nil {
			previous.next = element
		}
		previous = element
		size++
	}
	list := &List{
		head: first,
		size: size,
	}

	return list
}

// Size of list
func (l *List) Size() int {
	return l.size
}

// Push to end of list
func (l *List) Push(element int) {
	curr := l.head
	newElement := &Element{
		Value: element,
	}

	l.size++
	if curr == nil {
		l.head = newElement
	} else {
		for curr.next != nil {
			curr = curr.next
		}
		curr.next = newElement
	}
}

// Pop from end of list
func (l *List) Pop() (int, error) {
	curr := l.head
	if curr == nil {
		return 0, errors.New("List is empty")
	}

	if curr.next == nil {
		l.size--
		l.head = nil
		return curr.Value, nil
	}

	for true {
		prev := curr
		curr = curr.next
		if curr.next == nil {
			l.size--
			prev.next = nil
			return curr.Value, nil
		}
	}

	return 0, errors.New("Failed to pop")
}

// Generate array of elements
func (l *List) Array() []int {
	var result []int
	curr := l.head
	for curr != nil {
		result = append(result, curr.Value)
		curr = curr.next
	}
	return result
}

// Create a reverse list
func (l *List) Reverse() *List {
	arr := l.Array()

	for i, j := 0, len(arr)-1; i < j; i, j = i+1, j-1 {
		arr[i], arr[j] = arr[j], arr[i]
	}

	return New(arr)
}
