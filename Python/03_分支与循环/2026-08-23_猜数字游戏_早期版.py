""" 用代码制作小游戏 """
import random

counts = 3
answer = random.randint(1,10)

while counts > 0:
    temp = input("猜数字？：")
    guess = int(temp)

    if guess == answer:
        print("对了？？？")
        print("对了就对了呗！")
        break
    else:
        if guess < answer:
            print("傻子，太小了")
        else:
            print("太大了")
    counts = counts - 1    

print("不跟你玩了，傻孩子自己玩去吧")   
 
    
 
