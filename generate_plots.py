import os
import pandas as pd
import matplotlib.pyplot as plt
import numpy as np

os.makedirs('plots', exist_ok=True)


df = pd.read_csv('results.csv')


plt.style.use('seaborn-v0_8-whitegrid' if 'seaborn-v0_8-whitegrid' in plt.style.available else 'default')

# 1. Time vs N
plt.figure(figsize=(9, 5))
for alg in df['algorithm'].unique():
    subset = df[(df['algorithm'] == alg) & (df['input'] == 'random')]
    plt.plot(subset['n'], subset['time_ms'], marker='o', linewidth=2, label=alg)

plt.xscale('log')
plt.yscale('log')
plt.xlabel('Array Size (n)', fontsize=11)
plt.ylabel('Time (ms)', fontsize=11)
plt.title('Execution Time vs Array Size (Random Input)', fontsize=13, fontweight='bold')
plt.legend()
plt.grid(True, which="both", ls="--", alpha=0.5)
plt.tight_layout()
plt.savefig('plots/time_vs_n.png', dpi=300)
plt.close()

# 2. Depth vs N
plt.figure(figsize=(9, 5))
for alg in df['algorithm'].unique():
    subset = df[(df['algorithm'] == alg) & (df['input'] == 'sorted')]
    plt.plot(subset['n'], subset['max_depth'], marker='s', linewidth=2, label=alg)

plt.xscale('log')
plt.xlabel('Array Size (n)', fontsize=11)
plt.ylabel('Max Recursion Depth', fontsize=11)
plt.title('Max Recursion Depth vs Array Size (Sorted Input)', fontsize=13, fontweight='bold')
plt.legend()
plt.grid(True, which="both", ls="--", alpha=0.5)
plt.tight_layout()
plt.savefig('plots/depth_vs_n.png', dpi=300)
plt.close()

# 3. Ratio vs N
plt.figure(figsize=(9, 5))
for alg in df['algorithm'].unique():
    subset = df[(df['algorithm'] == alg) & (df['input'] == 'random')].copy()
    if alg == 'QuickSelect':
        subset['ratio'] = subset['comparisons'] / subset['n']
    else:
        subset['ratio'] = subset['comparisons'] / (subset['n'] * np.log2(subset['n']))
    plt.plot(subset['n'], subset['ratio'], marker='^', linewidth=2, label=f"{alg} Ratio")

plt.xscale('log')
plt.xlabel('Array Size (n)', fontsize=11)
plt.ylabel('Ratio (Comparisons / Complexity)', fontsize=11)
plt.title('Comparison Ratio vs Array Size (Convergence Check)', fontsize=13, fontweight='bold')
plt.legend()
plt.grid(True, which="both", ls="--", alpha=0.5)
plt.tight_layout()
plt.savefig('plots/ratio_vs_n.png', dpi=300)
plt.close()

print("Graphs generated successfully in plots/ folder!")