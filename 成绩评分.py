score = input("请输入你的分数")
score = int(score)

if 0 <= score < 60:
    print("傻子，你的是鸭蛋")
elif 60 <= score < 80:
    print("C")
elif 80 <= score < 90:
    print("B")
elif 90 <= score < 100:
    print("A")
elif 100 <= score < 120:
    print("SSS")
else:
    print("请输入0～120")
    
