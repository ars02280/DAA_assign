import os
import pandas as pd
import matplotlib.pyplot as plt
import numpy as np
import io

# Ваши реальные данные из CSV со всеми крайними случаями и дубликатами
csv_data = """algorithm,input,n,time_ms,comparisons,max_depth
MergeSort,random,1000,0,16203,8
MergeSort,random,10000,10,227055,11
MergeSort,random,100000,41,2939804,14
MergeSort,random,1000000,396,36158426,18
QuickSort,random,1000,8,16041,6
QuickSort,random,10000,7,231886,9
QuickSort,random,100000,42,3110766,11
QuickSort,random,1000000,418,36963121,13
QuickSelect,random,1000,0,5752,13
QuickSelect,random,10000,1,54722,14
QuickSelect,random,100000,9,439714,20
QuickSelect,random,1000000,43,4744039,25
MergeSort,sorted,1000,0,10876,8
MergeSort,sorted,10000,0,159248,11
MergeSort,sorted,100000,15,2044016,14
MergeSort,sorted,1000000,156,25342400,18
QuickSort,sorted,1000,0,17089,6
QuickSort,sorted,10000,7,221149,9
QuickSort,sorted,100000,26,2943784,10
QuickSort,sorted,1000000,266,36892914,13
QuickSelect,sorted,1000,0,4557,12
QuickSelect,sorted,10000,7,51182,17
QuickSelect,sorted,100000,5,516843,20
QuickSelect,sorted,1000000,24,4797383,29
MergeSort,duplicates,1000,0,15756,8
MergeSort,duplicates,10000,1,221500,11
MergeSort,duplicates,100000,26,2862159,14
MergeSort,duplicates,1000000,180,35193584,18
QuickSort,duplicates,1000,0,5530,2
QuickSort,duplicates,10000,0,58106,2
QuickSort,duplicates,100000,4,509329,3
QuickSort,duplicates,1000000,51,5496561,2
QuickSelect,duplicates,1000,0,3850,5
QuickSelect,duplicates,10000,0,38926,4
QuickSelect,duplicates,100000,10,460230,4
QuickSelect,duplicates,1000000,33,3299962,4"""

df = pd.read_csv(io.StringIO(csv_data))
os.makedirs('plots', exist_ok=True)

# Стили линеек для типов входных данных
styles = {'random': '-', 'sorted': '--', 'duplicates': ':'}
markers = {'MergeSort': 'o', 'QuickSort': 's', 'QuickSelect': '^'}

# 1. GRAPH 1: Time vs N (По всем входным данным)
plt.figure(figsize=(10, 6))
for alg in df['algorithm'].unique():
    for inp in df['input'].unique():
        subset = df[(df['algorithm'] == alg) & (df['input'] == inp)]
        plt.plot(subset['n'], subset['time_ms'],
                 linestyle=styles[inp], marker=markers[alg], linewidth=2,
                 label=f"{alg} ({inp})")

plt.xscale('log')
plt.yscale('log')
plt.xlabel('Array Size (n)', fontsize=11, fontweight='bold')
plt.ylabel('Execution Time (ms)', fontsize=11, fontweight='bold')
plt.title('Execution Time vs Array Size (All Input Types)', fontsize=13, fontweight='bold')
plt.legend(bbox_to_anchor=(1.05, 1), loc='upper left', fontsize=9)
plt.grid(True, which="both", ls="--", alpha=0.5)
plt.tight_layout()
plt.savefig('plots/time_vs_n.png', dpi=300)
plt.close()

# 2. GRAPH 2: Max Depth vs N (По всем входным данным — наглядно виден профит 3-way partition на duplicates)
plt.figure(figsize=(10, 6))
for alg in df['algorithm'].unique():
    for inp in df['input'].unique():
        subset = df[(df['algorithm'] == alg) & (df['input'] == inp)]
        plt.plot(subset['n'], subset['max_depth'],
                 linestyle=styles[inp], marker=markers[alg], linewidth=2,
                 label=f"{alg} ({inp})")

plt.xscale('log')
plt.xlabel('Array Size (n)', fontsize=11, fontweight='bold')
plt.ylabel('Max Recursion Depth', fontsize=11, fontweight='bold')
plt.title('Max Recursion Depth vs Array Size (Edge Cases & Duplicates)', fontsize=13, fontweight='bold')
plt.legend(bbox_to_anchor=(1.05, 1), loc='upper left', fontsize=9)
plt.grid(True, which="both", ls="--", alpha=0.5)
plt.tight_layout()
plt.savefig('plots/depth_vs_n.png', dpi=300)
plt.close()

# 3. GRAPH 3: Comparison Ratio vs N (По всем входным данным)
plt.figure(figsize=(10, 6))
for alg in df['algorithm'].unique():
    for inp in df['input'].unique():
        subset = df[(df['algorithm'] == alg) & (df['input'] == inp)].copy()
        if alg == 'QuickSelect':
            subset['ratio'] = subset['comparisons'] / subset['n']
        else:
            subset['ratio'] = subset['comparisons'] / (subset['n'] * np.log2(subset['n']))

        plt.plot(subset['n'], subset['ratio'],
                 linestyle=styles[inp], marker=markers[alg], linewidth=2,
                 label=f"{alg} ({inp})")

plt.xscale('log')
plt.xlabel('Array Size (n)', fontsize=11, fontweight='bold')
plt.ylabel('Ratio (Comparisons / Complexity)', fontsize=11, fontweight='bold')
plt.title('Comparison Ratio vs Array Size (Convergence with Duplicates)', fontsize=13, fontweight='bold')
plt.legend(bbox_to_anchor=(1.05, 1), loc='upper left', fontsize=9)
plt.grid(True, which="both", ls="--", alpha=0.5)
plt.tight_layout()
plt.savefig('plots/ratio_vs_n.png', dpi=300)
plt.close()

print("Новые цветные графики со ВСЕМИ типами данных (duplicates, sorted, random) успешно созданы в папке plots/!")
