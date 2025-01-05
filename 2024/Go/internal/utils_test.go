package internal

import (
	"math"
	"testing"
)

func TestAbs(t *testing.T) {
	const AbsErrorTolerance = 0

	tests := []struct {
		name  string
		input float64
		want  float64
	}{
		{"zero is zero, obliviously", 0, 0},
		{"should return positive integer given positive value", 1, 1},
		{"should return positive result given negative input", -1, 1},
		{"should return value within tolerance given the lowest negative integer value", -math.MaxInt, math.MaxInt},
		{"should return value within tolerance given the lowest negative float value", -math.MaxFloat64, math.MaxFloat64},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := Abs(tt.input); math.Abs(got-tt.want) > AbsErrorTolerance {
				t.Errorf("Abs() = %v, want %v", got, tt.want)
			}
		})
	}
}
