package main

import (
	"log"
	"os"
	"strconv"
	"time"

	"github.com/TinyRogue/advent/internal/solvers"
)

func main() {
	if len(os.Args) < 2 {
		log.Fatalln("Run the program with selected advent day <1, 25>. Usage: go run main.go <day>")
	}

	day, err := strconv.Atoi(os.Args[1])
	if err != nil || day < 1 || day > 25 {
		log.Fatalln("Invalid number of the day was selected. Available days are between 1 and 25.")
	}

	solver, err := solvers.SelectSolver(day)
	if err != nil {
		log.Fatalln(err)
	}

	start := map[solvers.Part]time.Time{}
	elapsed := map[solvers.Part]time.Duration{}
	results := map[solvers.Part]int{}

	start[solvers.First] = time.Now()
	results[solvers.First] = solver.Solve(solvers.First)
	elapsed[solvers.First] = time.Since(start[solvers.First])

	start[solvers.Second] = time.Now()
	results[solvers.Second] = solver.Solve(solvers.Second)
	elapsed[solvers.Second] = time.Since(start[solvers.Second])

	log.Printf("\n============= Results =============\n> The answer of the %v is %v - time taken %v\n> the answer of the %v is %v - time taken %v", solvers.First, results[solvers.First], elapsed[solvers.First], solvers.Second, results[solvers.Second], elapsed[solvers.Second])
}
