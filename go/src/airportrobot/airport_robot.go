package airportrobot

import "fmt"

// Write your code here.
// This exercise does not have tests for each individual task.
// Try to solve all the tasks first before running the tests.
type Greeter interface {
	LanguageName() string
	Greet(name string) string
}

type Italian struct{}

type Portuguese struct{}

func SayHello(name string, greeter Greeter) string {
	return fmt.Sprintf("I can speak %s: %s", greeter.LanguageName(), greeter.Greet(name))
}

func (lang Italian) LanguageName() string {
	return "Italian"
}

func (lang Italian) Greet(name string) string {
	return fmt.Sprintf("Ciao %s!", name)
}

func (lang Portuguese) LanguageName() string {
	return "Portuguese"
}

func (lang Portuguese) Greet(name string) string {
	return fmt.Sprintf("Olá %s!", name)
}
