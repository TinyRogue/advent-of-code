package internal

import (
	"fmt"
	"golang.org/x/exp/constraints"
	"os"
	"path/filepath"
)

const DataDirectory = "resources"

func Abs[T constraints.Float | constraints.Signed](value T) T {
	if value < 0 {
		return -value
	}
	return value
}

func ReadPuzzleInput(day int) ([]byte, error) {
	path := filepath.Join(DataDirectory, fmt.Sprintf("day%d", day), "puzzle_input.txt")
	content, err := os.ReadFile(path)
	if err != nil {
		return nil, fmt.Errorf("failed to load day %d resources: %w", day, err)
	}
	return content, nil
}
