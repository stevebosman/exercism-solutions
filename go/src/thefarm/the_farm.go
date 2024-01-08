package thefarm

import (
	"errors"
	"fmt"
)

func DivideFood(calc FodderCalculator, numCows int) (float64, error) {
	amount, error := calc.FodderAmount(numCows)
	if error != nil {
		return 0.0, error
	}

	var factor float64
	factor, error = calc.FatteningFactor()
	if error != nil {
		return 0.0, error
	}

	return amount * factor / float64(numCows), nil
}

func ValidateInputAndDivideFood(calc FodderCalculator, numCows int) (float64, error) {
	if numCows <= 0 {
		return 0.0, errors.New("invalid number of cows")
	}
	return DivideFood(calc, numCows)
}

type InvalidCowsError struct {
	cows    int
	message string
}

func (e InvalidCowsError) Error() string {
	return fmt.Sprintf("%d cows are invalid: %s", e.cows, e.message)
}

func ValidateNumberOfCows(numCows int) error {
	if numCows < 0 {
		return &InvalidCowsError{
			cows:    numCows,
			message: "there are no negative cows",
		}
	} else if numCows == 0 {
		return &InvalidCowsError{
			cows:    numCows,
			message: "no cows don't need food",
		}
	}
	return nil
}

// Your first steps could be to read through the tasks, and create
// these functions with their correct parameter lists and return types.
// The function body only needs to contain `panic("")`.
//
// This will make the tests compile, but they will fail.
// You can then implement the function logic one by one and see
// an increasing number of tests passing as you implement more
// functionality.
