print("简单小任务")
import random
num = random.randint(1,100)
print(num)
while True:
    try:
        nums = int(input("请输入生成的随机阿拉伯数字到对话框中"))
        if nums == num:
            print(f"你输入的是{nums},很好已经完成任务了")
        else:
            print("请输入正确的随机数字哦")
    except ValueError:
        print("输入错误，注意！！我要的是数字")

