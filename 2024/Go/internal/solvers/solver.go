package solvers

import "fmt"

type Solver interface {
	Solve(part Part) int
}

func SelectSolver(day int) (Solver, error) {
	switch day {
	case 1:
		return day1Instance()
	case 2:
		return day2Instance()
	case 3:
		return nil, fmt.Errorf("not implemented")
	case 4:
		return nil, fmt.Errorf("not implemented")
	case 5:
		return nil, fmt.Errorf("not implemented")
	case 6:
		return nil, fmt.Errorf("not implemented")
	case 7:
		return nil, fmt.Errorf("not implemented")
	case 8:
		return nil, fmt.Errorf("not implemented")
	case 9:
		return day9Instance()
	case 10:
		return nil, fmt.Errorf("not implemented")
	case 11:
		return nil, fmt.Errorf("not implemented")
	case 12:
		return nil, fmt.Errorf("not implemented")
	case 13:
		return nil, fmt.Errorf("not implemented")
	case 14:
		return nil, fmt.Errorf("not implemented")
	case 15:
		return nil, fmt.Errorf("not implemented")
	case 16:
		return nil, fmt.Errorf("not implemented")
	case 17:
		return nil, fmt.Errorf("not implemented")
	case 18:
		return nil, fmt.Errorf("not implemented")
	case 19:
		return nil, fmt.Errorf("not implemented")
	case 20:
		return nil, fmt.Errorf("not implemented")
	case 21:
		return nil, fmt.Errorf("not implemented")
	case 22:
		return nil, fmt.Errorf("not implemented")
	case 23:
		return nil, fmt.Errorf("not implemented")
	case 24:
		return nil, fmt.Errorf("not implemented")
	case 25:
		return nil, fmt.Errorf("not implemented")
	}

	return nil, fmt.Errorf("day must be within range <1, 25>, is %d", day)
}
