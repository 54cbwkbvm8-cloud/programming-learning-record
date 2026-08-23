# -*- coding: utf-8 -*-
# 第 3 题：打印三角形
#
# 关键知识：字符串可以乘数字，"* " * 3 会得到 "* * * "
#
# 思路：
#   - height 控制三角形的高度（几行）
#   - i 从 1 到 height，第几行就打印几个"* "

height = 5

for i in range(1, height + 1):
    print("* " * i)
