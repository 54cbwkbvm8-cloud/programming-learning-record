"""
简介：一个固定在 1 到 10 范围内的三次猜数字控制台小游戏。
功能：
- 最多允许输入三次猜测。
- 根据输入与答案 8 的大小关系输出提示。
- 猜中或次数用尽后结束程序。
用途：练习 input、while 循环、条件判断和 break。
最后修改：2026-08-17
"""

counts = 3

while counts > 0:
    temp = input("你是东西吗？：")
    guess = int(temp)

    if guess == 8:
        print("是你大坝？？？")
        print("你是春竹吗？这都不会回答")
        break
    else:
        if guess < 8:
            print("啊，对对对，你就不是个东西")
        else:
            print("啊，对对对，你就是个东西")
    counts = counts - 1    

print("不跟你玩了，傻孩子自己玩去吧")   
 
    
 
