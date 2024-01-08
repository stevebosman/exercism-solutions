package secret

// Reverse returns the reverse of the supplied slice.
func Reverse(s []string) {
	for i, j := 0, len(s)-1; i < j; i, j = i+1, j-1 {
		s[i], s[j] = s[j], s[i]
	}
}

// AddAction checks if right bit of code is set, and if it is adds action to actions
// code is then divided by two.
// AddAction returns the new code and the new actions slice.
func AddAction(code uint, actions []string, action string) (uint, []string) {
	if code%2 == 1 {
		actions = append(actions, action)
	}
	code = code / 2
	return code, actions
}

func Handshake(code uint) []string {
	var actions []string
	code, actions = AddAction(code, actions, "wink")
	code, actions = AddAction(code, actions, "double blink")
	code, actions = AddAction(code, actions, "close your eyes")
	code, actions = AddAction(code, actions, "jump")
	if code%2 == 1 {
		Reverse(actions)
	}
	return actions
}
