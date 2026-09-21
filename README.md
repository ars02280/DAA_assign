# Divide and Conquer Sorting & Selection Engine

## Description
Java implementation of MergeSort (with single memory buffer and Insertion Sort cutoff), 3-way QuickSort (with bounded stack depth), and QuickSelect.

## Project Structure
- `src/main/java/com/daa/algorithms`: Core algorithm implementations.
- `src/main/java/com/daa/metrics`: Execution metric tracking.
- `src/main/java/com/daa/benchmark`: CSV output generator.
- `src/test/java/com/daa`: JUnit 5 tests.

## Build and Run Instructions

### Prerequisites
- JDK 17 or higher
- Maven 3.6+
- Python 3 with `pandas`, `matplotlib`, and `numpy` (for plot generation)

### 1. Compile and Test
```bash
mvn clean test
