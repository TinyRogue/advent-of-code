package solvers

import (
	"reflect"
	"testing"
)

type input struct {
	listA []int
	listB []int
}

func assertNoMutation(tb testing.TB, fn func(a, b []int) int) {
	tb.Helper()
	input := [][]int{{3, 2, 1}, {1, 2, 3}}
	inputCopy := [][]int{
		make([]int, len(input[0])),
		make([]int, len(input[1])),
	}
	copy(inputCopy[0], input[0])
	copy(inputCopy[1], input[1])

	fn(input[0], input[1])
	if !reflect.DeepEqual(input, inputCopy) {
		tb.Errorf("was %v; is %v", input, inputCopy)
	}
}

func TestTotalDistance(t *testing.T) {
	tests := []struct {
		name  string
		input input
		want  int
	}{
		{name: "should return 0 on empty lists", input: input{listA: []int{}, listB: []int{}}, want: 0},
		{name: "should return 0 on lists with different length", input: input{listA: []int{1, 2, 3}, listB: []int{}}, want: 0},
		{name: "should calculate total distance", input: input{listA: []int{3, 4, 2, 1, 3, 3}, listB: []int{4, 3, 5, 3, 9, 3}}, want: 11},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := totalDistance(tt.input.listA, tt.input.listB); got != tt.want {
				t.Errorf("totalDistance() = %v, want %v", got, tt.want)
			}
		})
	}

	t.Run("should not update the collections content", func(t *testing.T) {
		assertNoMutation(t, totalDistance)
	})
}

func TestSimilarityScore(t *testing.T) {
	tests := []struct {
		name  string
		input input
		want  int
	}{
		{name: "should return 0 on empty lists", input: input{listA: []int{}, listB: []int{}}, want: 0},
		{name: "should return 0 on lists with different length", input: input{listA: []int{1, 2, 3}, listB: []int{}}, want: 0},
		{name: "should calculate similarity score", input: input{listA: []int{3, 4, 2, 1, 3, 3}, listB: []int{4, 3, 5, 3, 9, 3}}, want: 31},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := similarityScore(tt.input.listA, tt.input.listB); got != tt.want {
				t.Errorf("similarityScore() = %v, want %v", got, tt.want)
			}
		})
	}

	t.Run("should not update the collections content", func(t *testing.T) {
		assertNoMutation(t, totalDistance)
	})
}
