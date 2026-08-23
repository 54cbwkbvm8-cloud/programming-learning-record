# -*- coding: utf-8 -*-
# 第 1 题：九九乘法表
#
# 思路：
#   - 外层循环 i 代表"行"，从 1 到 9
#   - 内层循环 j 代表"列"，从 1 到 i（所以每行列数不超过行数）
#   - end="  " 表示这一句打印完不换行，只加两个空格
#   - 内层循环结束后 print() 负责换行

for i in range(1, 10):
    for j in range(1, i + 1):
        print(f"{j} x {i} = {i * j}", end="  ")
    print()
