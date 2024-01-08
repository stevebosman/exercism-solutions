package gross

var units = map[string]int{
	"quarter_of_a_dozen": 3,
	"half_of_a_dozen":    6,
	"dozen":              12,
	"small_gross":        120,
	"gross":              144,
	"great_gross":        1728,
}

// Units stores the Gross Store unit measurements.
func Units() map[string]int {
	return units
}

// NewBill creates a new bill.
func NewBill() map[string]int {
	return make(map[string]int)
}

// AddItem adds an item to customer bill.
func AddItem(bill, units map[string]int, item, unit string) bool {
	value, exists := units[unit]
	if !exists {
		return false
	}
	bill[item] = bill[item] + value
	return true
}

// RemoveItem removes an item from customer bill.
func RemoveItem(bill, units map[string]int, item, unit string) bool {
	uvalue, uexists := units[unit]
	if !uexists {
		return false
	}
	bvalue, bexists := bill[item]
	if !bexists {
		return false
	}
	newValue := bvalue - uvalue
	if newValue < 0 {
		return false
	}
	if newValue == 0 {
		delete(bill, item)
	} else {
		bill[item] = newValue
	}
	return true
}

// GetItem returns the quantity of an item that the customer has in his/her bill.
func GetItem(bill map[string]int, item string) (value int, exists bool) {
	value, exists = bill[item]
	return
}
