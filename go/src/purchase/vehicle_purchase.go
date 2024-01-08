package purchase

// NeedsLicense determines whether a license is needed to drive a type of vehicle. Only "car" and "truck" require a license.
func NeedsLicense(kind string) bool {
	return kind == "car" || kind == "truck"
}

// ChooseVehicle recommends a vehicle for selection. It always recommends the vehicle that comes first in lexicographical order.
func ChooseVehicle(option1, option2 string) string {
	var best string
	if option1 < option2 {
		best = option1
	} else {
		best = option2
	}
	return best + " is clearly the better choice."
}

// CalculateResellPrice calculates how much a vehicle can resell for at a certain age.
func CalculateResellPrice(originalPrice, age float64) float64 {
	var factor float64
	if age >= 10.0 {
		factor = 0.5
	} else if age >= 3.0 {
		factor = 0.7
	} else {
		factor = 0.8
	}
	return originalPrice * factor
}
