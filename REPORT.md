# Performance Analysis: Divide-and-Conquer Algorithms

## 1. Asymptotic Complexity Bounds

| Algorithm | Best Case | Average Case | Worst Case | Operational Reason |
| :--- | :--- | :--- | :--- | :--- |
| **MergeSort** | $\Theta(n \log n)$ | $\Theta(n \log n)$ | $\Theta(n \log n)$ | Array is always partitioned into halves regardless of ordering. |
| **QuickSort** | $\Omega(n)$ | $\Theta(n \log n)$ | $O(n \log n)$ | Best: 3-way partition handles duplicates linearly; Worst: bounded depth caps recursion. |
| **QuickSelect** | $\Omega(n)$ | $\Theta(n)$ | $O(n)$ | Random pivot guarantees $O(n)$ expected split on single target subproblem. |
| **Insertion Sort** | $\Omega(n)$ | $\Theta(n^2)$ | $O(n^2)$ | Best: presorted array needs 0 swaps; Worst: reversed array needs max swaps. |

---

## 2. Recurrences & Master Theorem Solutions

### MergeSort
- **Recurrence Relation:** $T(n) = 2T(n/2) + \Theta(n)$
- **Master Theorem Parameters:** $a = 2$, $b = 2$, $f(n) = \Theta(n)$
- **Case:** Case 2 ($f(n) = \Theta(n^{\log_b a}) = \Theta(n^1)$)
- **Solution:** $T(n) = \Theta(n \log n)$

### QuickSort (Balanced Split Assumption)
- **Recurrence Relation:** $T(n) = 2T(n/2) + \Theta(n)$
- **Master Theorem Parameters:** $a = 2$, $b = 2$, $f(n) = \Theta(n)$
- **Case:** Case 2 ($f(n) = \Theta(n^{\log_b a})$)
- **Solution:** $T(n) = \Theta(n \log n)$
- **Explanation:** Random pivot selection avoids worst-case splits on sorted inputs, maintaining an expected balanced partition ($25/75$ or better) with high probability, yielding $O(n \log n)$ average time.

### QuickSelect (Balanced Split Assumption)
- **Recurrence Relation:** $T(n) = 1T(n/2) + \Theta(n)$
- **Master Theorem Parameters:** $a = 1$, $b = 2$, $f(n) = \Theta(n)$
- **Case:** Case 3 ($f(n) = \Omega(n^{\log_2 1 + \epsilon}) = \Omega(n^0)$ with regularity condition $1 \cdot (n/2) \le c \cdot n$ for $c = 1/2 < 1$)
- **Solution:** $T(n) = \Theta(n)$

---

## 3. Empirical Analysis & Visualizations

### Performance Visualizations (All Input Types Included)

#### 1. Execution Time vs Array Size
![Execution Time vs Size](plots/time_vs_n.png)

#### 2. Max Recursion Depth vs Array Size (Edge Cases & Duplicates)
![Max Recursion Depth vs Size](plots/depth_vs_n.png)

#### 3. Comparison Ratio vs Array Size (Convergence Check)
![Comparison Ratio vs Size](plots/ratio_vs_n.png)

### Ratio Convergence Check ($\Theta$-bound Verification)

#### MergeSort (random)
```text
N          | Comparisons     | Theoretical Growth   | Ratio     
------------------------------------------------------------------
1000       | 16203           | 9965.78              | 1.6259    
10000      | 227055          | 132877.12            | 1.7088    
100000     | 2939804         | 1660964.04           | 1.7700    
1000000    | 36158426        | 19931568.57          | 1.8141