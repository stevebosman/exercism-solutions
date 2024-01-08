package lasagnamaster

func PreparationTime(layers []string, time int) int {
	if time == 0 {
		time = 2
	}
	return len(layers) * time
}

func Quantities(layers []string) (noodles int, sauce float64) {
	noodles = 0
	sauce = 0.0
	for _, layer := range layers {
		if layer == "noodles" {
			noodles += 50
		} else if layer == "sauce" {
			sauce += 0.2
		}
	}
	return
}

func AddSecretIngredient(friendsList []string, myList []string) {
	myList[len(myList)-1] = friendsList[len(friendsList)-1]
}

func ScaleRecipe(portions []float64, numberOfPortions int) []float64 {
	var scaled []float64
	factor := float64(numberOfPortions) / 2.0
	for _, portion := range portions {
		scaled = append(scaled, portion*factor)
	}
	return scaled
}
