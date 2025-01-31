package solvers

import (
	"bytes"
	"testing"
)

func TestChecksumWithDiskFragmentation(t *testing.T) {
	tests := []struct {
		name   string
		input  []byte
		result int
	}{
		{name: "disk fragmentation", input: []byte{'2', '2', '3', '3'}, result: 9},
		{name: "partial file fragmentation", input: []byte{'1', '2', '3', '4', '5'}, result: 60},
		{name: "aoc example", input: []byte{'2', '3', '3', '3', '1', '3', '3', '1', '2', '1', '4', '1', '4', '1', '3', '1', '4', '0', '2'}, result: 1928},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			diskUtility := DiskUtility{
				volume: tt.input,
			}

			checksum := diskUtility.checksumWithDiskFragmentation()
			if checksum != tt.result {
				t.Errorf("Expected %v, got %v", tt.result, checksum)
			}
		})
	}
}

func TestChecksumWithoutDiskFragmentation(t *testing.T) {
	tests := []struct {
		name   string
		input  []byte
		result int
	}{
		{name: "no moving file at all", input: []byte{'2', '2', '3', '3'}, result: 15},
		{name: "no moving file at all", input: []byte{'1', '2', '3', '4', '5'}, result: 132},
		{name: "with perfect file compressing", input: []byte{'2', '5', '3', '2', '2'}, result: 25},
		{name: "with gaps", input: []byte{'2', '5', '3', '2', '4'}, result: 52},
		{name: "with zero blocks", input: []byte{'2', '5', '0', '2', '4', '1'}, result: 28},
		{name: "aoc example", input: []byte{'2', '3', '3', '3', '1', '3', '3', '1', '2', '1', '4', '1', '4', '1', '3', '1', '4', '0', '2'}, result: 2858},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			diskUtility := DiskUtility{
				volume: tt.input,
			}

			checksum := diskUtility.checksumWithoutDiskFragmentation()
			if checksum != tt.result {
				t.Errorf("Expected %v, got %v", tt.result, checksum)
			}
		})
	}
}

func TestChecksumPurity(t *testing.T) {
	tests := []struct {
		name         string
		checksumFunc func(*DiskUtility) int
	}{
		{"WithDiskFragmentation", (*DiskUtility).checksumWithDiskFragmentation},
		{"WithoutDiskFragmentation", (*DiskUtility).checksumWithoutDiskFragmentation},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			diskUtility := DiskUtility{
				volume: []byte{'2', '2', '3', '3'},
			}

			volumeSnapshots := make([][]byte, 3)
			volumeSnapshots[0] = append([]byte(nil), diskUtility.volume...)

			res1 := tt.checksumFunc(&diskUtility)
			volumeSnapshots[1] = append([]byte(nil), diskUtility.volume...)
			res2 := tt.checksumFunc(&diskUtility)
			volumeSnapshots[2] = append([]byte(nil), diskUtility.volume...)

			if !bytes.Equal(volumeSnapshots[0], volumeSnapshots[1]) || !bytes.Equal(volumeSnapshots[1], volumeSnapshots[2]) {
				t.Errorf("%s: Data has been altered - method must not change the contents of the volume", tt.name)
			}

			if res1 != res2 {
				t.Errorf("%s: Results of the same method are different", tt.name)
			}
		})
	}
}
