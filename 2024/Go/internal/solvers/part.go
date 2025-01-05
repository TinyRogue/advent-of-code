package solvers

type Part int

const (
	First Part = iota
	Second
)

var partName = map[Part]string{
	First:  "first part",
	Second: "second part",
}

func (p Part) String() string {
	return partName[p]
}
