package linkedlist

import "errors"

// Define List and Node types here.
type Node struct {
	Value    interface{}
	previous *Node
	next     *Node
}
type List struct {
	head *Node
	tail *Node
}

// Create new list
func NewList(elements ...interface{}) *List {
	list := &List{}
	for _, element := range elements {
		list.Push(element)
	}
	return list
}

// Next node
func (n *Node) Next() *Node {
	return n.next
}

// Previous node
func (n *Node) Prev() *Node {
	return n.previous
}

// Unshift to start of List
func (l *List) Unshift(v interface{}) {
	oldhead := l.head
	node := &Node{
		Value: v,
		next:  oldhead,
	}
	if oldhead != nil {
		oldhead.previous = node
	} else {
		l.tail = node
	}
	l.head = node
}

// Push to end of List
func (l *List) Push(v interface{}) {
	oldTail := l.tail
	node := &Node{
		Value:    v,
		previous: oldTail,
	}
	if oldTail != nil {
		oldTail.next = node
	} else {
		l.head = node
	}
	l.tail = node
}

// Shift from start of List
func (l *List) Shift() (interface{}, error) {
	head := l.head
	if head == nil {
		return nil, errors.New("List is empty")
	} else {
		next := head.next
		if next == nil {
			l.tail = nil
			l.head = nil
		} else {
			next.previous = nil
			l.head = next
		}
		return head.Value, nil
	}
}

// pop on end of List
func (l *List) Pop() (interface{}, error) {
	tail := l.tail
	if tail == nil {
		return nil, errors.New("List is empty")
	} else {
		previous := tail.previous
		if previous == nil {
			l.tail = nil
			l.head = nil
		} else {
			previous.next = nil
			l.tail = previous
		}
		return tail.Value, nil
	}
}

// Reverse the list
func (l *List) Reverse() {
	curr := l.head
	if curr != nil && curr.next != nil {
		for curr != nil {
			curr.next, curr.previous = curr.previous, curr.next
			curr = curr.previous
		}
		l.tail, l.head = l.head, l.tail
	}
}

// First node in list
func (l *List) First() *Node {
	return l.head
}

// Last node in list
func (l *List) Last() *Node {
	return l.tail
}
