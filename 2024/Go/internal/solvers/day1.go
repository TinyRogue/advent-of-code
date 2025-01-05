package solvers

import (
	"fmt"
	"github.com/TinyRogue/advent/internal"
	"sort"
	"strconv"
	"strings"
)

func (day *day1) Solve(part Part) int {
	if part == First {
		return totalDistance(day.listA, day.listB)
	}
	return similarityScore(day.listA, day.listB)
}

type day1 struct {
	listA []int
	listB []int
}

func day1Instance() (*day1, error) {
	content, err := internal.ReadPuzzleInput(1)
	if err != nil {
		return nil, fmt.Errorf("failed to load day 1 resources: %w", err)
	}

	day1 := day1{}
	lines := strings.Split(string(content), "\n")
	for _, line := range lines {
		parts := strings.Fields(line)
		numA, err1 := strconv.Atoi(parts[0])
		numB, err2 := strconv.Atoi(parts[1])
		if err1 != nil || err2 != nil {
			return nil, fmt.Errorf("failed to load day 1 resources: %w, %w", err1, err2)
		}
		day1.listA = append(day1.listA, numA)
		day1.listB = append(day1.listB, numB)
	}

	return &day1, nil
}

func totalDistance(a, b []int) (distance int) {
	if len(a) != len(b) {
		return 0
	}

	ab := [][]int{
		make([]int, len(a)),
		make([]int, len(b)),
	}

	copy(ab[0], a)
	copy(ab[1], b)

	sort.Ints(ab[0])
	sort.Ints(ab[1])

	for i := 0; i < len(ab[0]); i++ {
		distance += internal.Abs(ab[0][i] - ab[1][i])
	}

	return distance
}

func similarityScore(a, b []int) (similarity int) {
	histogram := make(map[int]int)
	for _, value := range b {
		histogram[value]++
	}

	for _, value := range a {
		similarity += histogram[value] * value
	}
	return similarity
}
