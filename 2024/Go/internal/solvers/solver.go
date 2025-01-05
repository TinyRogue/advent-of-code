package solvers

import "fmt"

type Solver interface {
	Solve(part Part) int
}

func SelectSolver(day int) (Solver, error) {
	switch day {
	case 1:
		return day1Instance()
	}
	return nil, fmt.Errorf("day must be within range <1, 25>, is %d", day)
}
