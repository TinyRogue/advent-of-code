package solvers

import (
	"fmt"
	"slices"

	"github.com/TinyRogue/advent/internal"
)

type DiskUtility struct {
	volume []byte
}

func (d *DiskUtility) Solve(part Part) int {
	if part == First {
		return d.checksumWithDiskFragmentation()
	}
	return d.checksumWithoutDiskFragmentation()
}

func day9Instance() (*DiskUtility, error) {
	content, err := internal.ReadPuzzleInput(9)
	if err != nil {
		return nil, fmt.Errorf("failed to load day 9 resources: %w", err)
	}

	return &DiskUtility{
		volume: content,
	}, nil
}

func (d *DiskUtility) checksumWithDiskFragmentation() int {
	disk := DiskUtility{
		volume: append([]byte(nil), d.volume...),
	}
	disk.rightTrimEmptySpace()

	lastFileID := len(disk.volume) / 2
	var position, checksum, fileID int

	for i, j := 0, len(disk.volume)-1; i <= j; {
		if i%2 == 0 {
			occupiedMemoryBlocks := int(disk.volume[i] - '0')
			endBlockPosition := position + occupiedMemoryBlocks
			for ; position < endBlockPosition; position++ {
				checksum += position * fileID
			}
			fileID++
			i++
			continue
		}
		freeMemoryBlocks := int(disk.volume[i] - '0')
		lastFileMemoryBlocks := int(disk.volume[j] - '0')
		endBlockPosition := position + min(freeMemoryBlocks, lastFileMemoryBlocks)
		for ; position < endBlockPosition; position++ {
			checksum += position * lastFileID
		}
		if freeMemoryBlocks > lastFileMemoryBlocks {
			disk.volume[i] = byte(freeMemoryBlocks - lastFileMemoryBlocks + '0')
			j -= 2
			lastFileID--
		} else {
			disk.volume[j] = byte(lastFileMemoryBlocks - freeMemoryBlocks + '0')
			i++
		}
	}
	return checksum
}

type MemoryBlock struct {
	fileID  int
	size    int
	offset  int
	touched bool
}

const emptyBlock = -1

func (d *DiskUtility) checksumWithoutDiskFragmentation() int {
	disk := DiskUtility{
		volume: append([]byte(nil), d.volume...),
	}
	disk.rightTrimEmptySpace()

	memory := make([]MemoryBlock, len(disk.volume))
	offset, fileID := 0, 0
	for i, value := range disk.volume {
		blockSize := int(value - '0')
		if i%2 == 1 {
			memory[i] = MemoryBlock{
				fileID:  emptyBlock,
				size:    blockSize,
				offset:  offset,
				touched: false,
			}
		} else {
			memory[i] = MemoryBlock{
				fileID:  fileID,
				size:    blockSize,
				offset:  offset,
				touched: false,
			}
			fileID++
		}
		offset += blockSize
	}

	for end := len(memory) - 1; end > 0; {
		if memory[end].touched || memory[end].fileID == emptyBlock {
			end--
			continue
		}

		for start := 1; end > start; {
			if memory[start].fileID != emptyBlock || memory[end].size > memory[start].size {
				start++
				continue
			}

			movedBlock := MemoryBlock{
				fileID:  memory[end].fileID,
				size:    memory[end].size,
				offset:  memory[start].offset,
				touched: true,
			}
			memory[end].fileID = emptyBlock

			if memory[start].size > movedBlock.size {
				freeMemBlock := MemoryBlock{
					fileID: emptyBlock,
					size:   memory[start].size - movedBlock.size,
					offset: memory[start].offset + movedBlock.size,
				}
				memory = slices.Insert(memory, start+1, freeMemBlock)
				end++
			}
			memory[start] = movedBlock
			break
		}

		end--
	}

	checksum := 0
	for _, memBlock := range memory {
		if memBlock.fileID == emptyBlock {
			continue
		}

		for i := 0; i < memBlock.size; i++ {
			checksum += memBlock.fileID * (memBlock.offset + i)
		}
	}

	return checksum
}

func (d *DiskUtility) rightTrimEmptySpace() {
	if len(d.volume)%2 == 0 {
		d.volume = d.volume[:len(d.volume)-1]
	}
}

// index: free space
// 2: 3, 8: 3, 12: 3
//

// offset: len & file_id
//

// 00...111...2...333.44.5555.6666.777.888899
// 0099.111...2...333.44.5555.6666.777.8888..
// 0099.1117772...333.44.5555.6666.....8888..
// 0099.111777244.333....5555.6666.....8888..
// 00992111777.44.333....5555.6666.....8888..

// 2 3 3 3 1 3 3 1 2 1 4 1 4 1 3 1 4 0 2
