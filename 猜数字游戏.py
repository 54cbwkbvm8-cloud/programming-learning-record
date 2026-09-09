""" 用代码制作小游戏 """  # 文件开头的说明字符串（docstring），描述这是一个小游戏
import random  # 导入 random 模块，用于生成随机数

counts = 3  # 最多允许猜测 3 次
answer = random.randint(1,10)  # 随机生成一个 1 到 10 之间的整数作为答案

while counts > 0:  # 只要还有猜测次数就继续循环
    temp = input("猜数字？：")  # 提示用户输入一个数，以字符串形式保存
    guess = int(temp)  # 把输入的内容转成整数

    if guess == answer:  # 如果猜的数字与答案相等
        print("对了？？？")  # 打印猜对提示
        print("对了就对了呗！")  # 打印另一句猜对提示
        break  # 猜对之后结束循环
    else:  # 没有猜对
        if guess < answer:  # 猜的数字小于答案
            print("傻子，太小了")  # 提示猜得太小
        else:  # 否则猜的数字大于答案
            print("太大了")  # 提示猜得太大
    counts = counts - 1  # 每猜一次，剩余次数减 1

print("不跟你玩了，傻孩子自己玩去吧")   # 游戏结束（次数用尽或猜中跳出）后打印提示
